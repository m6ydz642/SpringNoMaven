package project.rasp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.javassist.compiler.ast.Keyword;
import org.h2.util.json.JSONArray;
import org.h2.util.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import lombok.val;
import project.rasp.Customfunction;
import project.rasp.mapper.BoardMapper;
import project.rasp.mapper.UserMapper;
import project.rasp.model.Board;
import project.rasp.model.Comment;
import project.rasp.model.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BoardController {

	 @Autowired
	BoardMapper boardmapper;
	private Board board;


	BoardMapper commemtmapper;

	UserMapper usermapper;
	private Comment comment;
	
	private Customfunction customfunction = new Customfunction(); 
	// Customfunction customfunction; // 클래스에서 빈 주입 해놓음
	// 빈주입 할줄 몰라서 객체생성해서 씀 ^^ㅋㅋㅋㅋㅋㅋㅋ

	Board a = new Board(); // 일단 스프링 의존주입 같은거 몰라서 static으로 함
	// 다른 함수에서 다시 객체생성하는 순간 주소가 달라서 초기화 됨
	// 나중에 값 중복이나 다른곳에 있는 자료가 뜨면 여길 보면 됨

	// 아마 스프링은 원래 Service 만들어서 return해야 됨
	// 객체로 만들어도 상관은 없지만 스프링은 그걸 써서

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	
	/*********************************************************/
	public ModelAndView AnyRedirect(String url) {
		// 주소 안보이게 하는 리다이렉트 함수
		RedirectView rv = new RedirectView(url);
		rv.setExposeModelAttributes(false);
		System.out.println("입력받은 Redirect주소 : " + url);
		// RequestMapping컨트롤러에서는 String타입으로 리턴을 받아야 만
		// .jsp를 찾아가는데 ModelAndView에서는 return이 객체 생성이라
		// .jsp를 못찾기 때문에 AnyRedirect라는 함수를 호출
		// servlet-context.xml설정이나 다른 ModelView같은 함수도 찾아보고는
		// 싶지만 다음에.......................

		// 사실 보면 주소를 2중 호출하는 거라 효율적이진 않음
		return new ModelAndView(rv);

	}

	/*********************************************************/
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String list(Model model) throws Exception {
		logger.info("게시판 호출");

		List list = null;
		list = boardmapper.getContentlist(board);
		model.addAttribute("contentlist", list); // 값 넣음
		System.out.println("들어간 list 갯수 : " + list.size());
		
		
		/* 잠시 보류 오전 11:09 2021-01-20
		 * System.out.println("댓글 개수 호출 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"); int
		 * commentcount = boardmapper.CommentCount(1);
		 * model.addAttribute("commentcount",commentcount);
		 * System.out.println("댓글 개수 : " + commentcount);
		 */
		
		
		return "board"; // board.jsp로 이동
	}

	@RequestMapping(value = "/boardwrite", method = RequestMethod.GET)
	public String BoardWrite(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {
		System.out.println("게시판 글작성 호출");

	
		// 객체 주입해서 스프링 방식으로 진행 하려고 했는데
		// 잘 안되서 일단 객체생성으로 함 ㅠㅠ
		String value = customfunction.MemberCheck(session, request, response);
		
		System.out.println("보드 글작성 value값  확인 : " + value);
		// return "boardwrite";
		return value;

	}

	@RequestMapping(value = "/boarddelete", method = RequestMethod.GET)
	public String BoardDelete(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws IOException {
		System.out.println("게시판 글삭제 호출");
		String Delete_Number = request.getParameter("board_id"); // content에 input태그에 name값을 계속씀
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("글삭제 호출 글 번호 : " + Delete_Number);
		// 조만간 세션 검사해서 아이디 확인여부 넣어야 됨
		// board_id값 받아야 됨
		
		Map map = new HashMap();
		map.put("board_id", Delete_Number); // 글번호 
		map.put("userid", session.getAttribute("loginid"));
		System.out.println("Delete_Number map key (board_num) : " + Delete_Number);
		System.out.println("Delete_Number map key (loginid) : " + session.getAttribute("loginid"));
		// 현재 세션에서 존재하는 아이디
		// 자기 아이디 아닌상태로 수정 요청하면 DB조회시 결과가 달라서 조회 안되게
		
		
		boolean ModifiyResult = false; // false로 초기화

		try {
			

			 ModifiyResult = boardmapper.UpdateValidationContent(map); // 삭제 요청시 검증
									// 함수이름이 update인건 update하기전 조회확인용으로 하려고 했는데 
									// 쿼리문이 select라서 그냥 회원이 한게 맞냐만 검증하는 거라
			 						//	그냥 써도 무방
			
			
	
			System.out.println("글 삭제 검증 쿼리 결과 " + ModifiyResult);
		
			Board boardDb = boardmapper.Content(Integer.parseInt(Delete_Number)); // 객체타입으로 넣음

			model.addAttribute("boardcontent", boardDb); // 객체로 값 넣음
			
			boardmapper.DeleteContent(Integer.parseInt(Delete_Number)); // 글삭제 요청 int로 전송
		} catch (BindingException e) {
			System.out.println("글삭제 BindingException 예외 발생 : " + e);
			customfunction.UserValidationFail(session, request, response, ModifiyResult);
			return "board";
			
		}
		
		
		
		

		return "redirect:board";

	}

	/*
	 * @RequestMapping(value = "/boardmodify", method = RequestMethod.GET) public
	 * ModelAndView BoardModify(HttpServletRequest request) { // ModelAndView방식 쓰려고
	 * 형태 바꿈 System.out.println("게시판 수정 호출"); String Modify_Number =
	 * request.getParameter("board_id"); // content에 input태그에 name값을 계속씀
	 * System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
	 * System.out.println("글수정 호출 글 번호 : " + Modify_Number); // 조만간 세션 검사해서 아이디 확인여부
	 * 넣어야 됨 // board_id값 받아야 됨 // return "boardmodifywrite"; // 글 작성페이지로 이동하기
	 * 
	 * RedirectView rv = new RedirectView("/boardmodifywrite");
	 * rv.setExposeModelAttributes(false); // 주소 안보이게
	 * 
	 * return new ModelAndView(rv); // 이거 문제가 String타입이 아니라서 .jsp를 알아서 못찾음... //
	 * web.xml설정 건드리던지 다른 함수로 다시 보내서 // String으로 반환을 시키던지 해야됨 일단은 ↓ 밑에 매핑으로 보냄 }
	 */

	@RequestMapping(value = "/boardmodify", method = RequestMethod.GET)
	public ModelAndView BoardModify(HttpServletRequest request) {
		// ModelAndView방식 쓰려고 형태 바꿈
		System.out.println("게시판 수정 호출");
		String Modify_Number = request.getParameter("board_id"); // content에 input태그에 name값을 계속씀
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("글수정 호출 글 번호 : " + Modify_Number);

		// redirect밑에 주소 안보이게 하려고 false로 해놔서 안나옴

		// 조만간 세션 검사해서 아이디 확인여부 넣어야 됨
		// board_id값 받아야 됨
		// return "boardmodifywrite"; // 글 작성페이지로 이동하기

		// RedirectView rv = new
		// RedirectView("/boardmodifywrite?board_id="+Modify_Number);
		RedirectView rv = new RedirectView("/boardmodifywrite");
		rv.setExposeModelAttributes(false); // 주소 안보이게
		a.setBoard_id(Integer.parseInt(Modify_Number)); // 게터로 전달
		// board.setBoard_id(Integer.parseInt(Modify_Number));
		System.out.println("setter로 입력된 Board_id : " + a.getBoard_id());
		return new ModelAndView(rv);
		// 이거 문제가 String타입이 아니라서 .jsp를 알아서 못찾음...
		// web.xml설정 건드리던지 다른 함수로 다시 보내서
		// String으로 반환을 시키던지 해야됨 일단은 ↓ 밑에 매핑으로 보냄
	}

	/*********************************************************/

	@RequestMapping(value = "/wow", method = RequestMethod.GET) // post로 바꿔야 됨
	public String wow(HttpServletRequest request) {
		System.out.println("wow  페이지 호출");
		logger.debug("와우로거 테스트");
		String Modify_Number_Write = request.getParameter("board_id"); // content에 input태그에 name값을 계속씀
		logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 와우로거");
		System.out.println("wow 페이지할 글 번호 : " + Modify_Number_Write);
		// 조만간 세션 검사해서 아이디 확인여부 넣어야 됨
		// board_id값 받아야 됨
		AnyRedirect("wowow"); // AnyRedirect에서 wowow로 보냈으면 주소창 숨김전환후
		// return으로 wowow.jsp를 찾아감
		return "wowow"; // 해당 jsp로 이동

	}

	/**
	 * @throws IOException *******************************************************/
	@RequestMapping(value = "/boardmodifywrite", method = RequestMethod.GET) // post로 바꿔야 됨
	public String BoardModifyWrite(HttpServletRequest request, Model model, HttpSession session, HttpServletResponse response) throws IOException {
		System.out.println("게시판 수정할 페이지 호출");
		// String Modify_Number_Write = request.getParameter("board_id"); // content에
		// input태그에 name값을 계속씀
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
		// System.out.println("글수정 Request 글 번호 : " + Modify_Number_Write);
		// 조만간 세션 검사해서 아이디 확인여부 넣어야 됨
		// board_id값 받아야 됨
		// int board_num = Integer.parseInt(request.getParameter("board_id"));
		//

		String DBcheckValue = customfunction.MemberDB_Check(session, request, response);
		System.out.println("DBcheckValue 값 : " + DBcheckValue);
		
		int board_num = a.getBoard_id();
		System.out.println("a.getBoard_id = " + a.getBoard_id());
		
		Map map = new HashMap();
		map.put("board_id", board_num); // 글번호 
		map.put("userid", session.getAttribute("loginid")); 
		// 현재 세션에서 존재하는 아이디
		// 자기 아이디 아닌상태로 수정 요청하면 DB조회시 결과가 달라서 조회 안됨
		System.out.println("boardmodifywrite map key (board_num) : " + board_num);
		System.out.println("boardmodifywrite map key (loginid) : " + session.getAttribute("loginid"));
		
		boolean ModifiyResult = false;
		
		System.out.println("map.get = " + map.get("userid"));
		
		try {
			ModifiyResult = boardmapper.UpdateValidationContent(map); // 수정 요청시 검증	
		
			
			System.out.println("글 수정 쿼리 결과 " + ModifiyResult);
		
			Board boardDb = boardmapper.Content(board_num); // 객체타입으로 넣음

			model.addAttribute("boardcontent", boardDb); // 객체로 값 넣음
			
		} catch (BindingException e) {
			customfunction.UserValidationFail(session, request, response, ModifiyResult);
			// 예외 발생시 한번더 alert창을 띄우기 위해 함수 호출
			
			System.out.println("글수정 예외 발생 : " + e);
			// 아이디 존재 안하면 쿼리안되서 false나오면 if문 처리하면 되는데 
			// 얘들이 계속 boolean타입인데 계속 null 나와서 그냥 예외로 처리함 ㅋㅋ
		}
		
	
		return "boardmodifywrite"; // 해당 jsp로 이동

	}

	@RequestMapping(value = "/boardmodifywritecomplete", method = RequestMethod.POST) // post로 바꿔야 됨
	public String BoardModifyWriteComplete(HttpServletRequest request, HttpSession session) {
		System.out.println("게시판 수정후 글작성 호출");
		String Modify_Number_Write = request.getParameter("board_id"); // content에 input태그에 name값을 계속씀
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("글수정 후 글작성 번호 : " + Modify_Number_Write);
		// 조만간 세션 검사해서 아이디 확인여부 넣어야 됨
		// board_id값 받아야 됨

		String writer = (String) session.getAttribute("writer");
		// 사용자 세션 아직 null임 로그인 기능 안해서
		String subject = request.getParameter("modifysubject");

		String content = request.getParameter("modifycontent");
		System.out.println("글수정 완료 - 제목 : " + subject);
		System.out.println("글수정 완료 - 내용 : " + content);

		Map map = new HashMap();
		// map.put("writer", writer); // 아직 널 값임
		map.put("subject", subject);
		map.put("content", content);
		map.put("board_id", Modify_Number_Write);
		boardmapper.UpdateContent(map);

		return "redirect:boardcontent?board_id=" + Modify_Number_Write;
		// return "test";

	}

	@RequestMapping(value = "/boardcontent", method = RequestMethod.GET)
	public String boardcontent(HttpServletRequest request, Model model) throws Exception {

		int board_num = Integer.parseInt(request.getParameter("board_id"));

		Board boardDb = boardmapper.Content(board_num); // 객체타입으로 넣음

		System.out.println("전달 받은 값 : " + boardDb);

		model.addAttribute("boardcontent", boardDb); // 객체로 값 넣음
		// jstl에서는 불러올때 c:out해서 쓰면 됨

		System.out.println("보드 내용 ");
		logger.info("게시글 내용 호출");
		logger.info("board_id = " + boardDb);
		System.out.println("글번호 = " + boardDb.getBoard_id());
		System.out.println("작성자 = " + boardDb.getUsername());
		System.out.println("게시글 제목 = " + boardDb.getSubject());
		System.out.println("게시글 내용 = " + boardDb.getContent());
		
		System.out.println("조회수 결과 : "); // 조회수 증가
		boardmapper.UpdateViewCount(boardDb.getBoard_id());
		
		// comment(board_num); // 댓글 호출

		/*****************************************************/
		System.out.println("@@@@@@@@@@@@@@@@@@@@@댓글 리스트 호출@@@@@@@@@@@@@@@@@@@");
		// 댓글
		System.out.println("전달받은 댓글 board_id번호 " + board_num);
		List commentlist = boardmapper.Comment(board_num); // 댓글객체 가져옴
		System.out.println("commentmapper : " + boardmapper.Comment(board_num));
		System.out.println("댓글 갯수 : " + commentlist.size());
		// System.out.println("댓글 번호 : 보류중 : " + comment.getUsername() );
		model.addAttribute("comment", commentlist);
		
		/*****************************************************/
		return "boardcontent";

	}

	
	@RequestMapping(value = "/writecomplete", method = RequestMethod.POST)
	public String writecomplete(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		System.out.println("게시글 작성 완료 호출");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// String writer = (String) session.getAttribute("writer");
		// String writer =  (String) session.getAttribute("logininfo"); // 로그인 정보 확인
		
		String subject = request.getParameter("subject");

		String content = request.getParameter("content");

		 String writer = request.getParameter("writer");
		System.out.println("작성자 파라메터로 한번 받아와 봄 : " + writer);
		// 글수정
		Map map = new HashMap();
		map.put("userid", writer.trim()); // 공백제거 아...짱나네 ㅋㅋㅋㅋㅋㅋㅋㅋ
		map.put("subject", subject);
		map.put("content", content);
		boardmapper.WriteContent(map);

		System.out.println("--------------------------------------");
		System.out.println("게시글 작성자 : " + writer);
		System.out.println("글제목 : " + subject);
		System.out.println("내용 : " + content);
		System.out.println("--------------------------------------");

		PrintWriter out = response.getWriter();

		
		  if (writer == null) { // 로그인 안한 사용자가 접근시
		  
		  out.println("<script language='javascript'> ");
		  out.println("alert('로그인부터 하세요 ^^;');"); //
		  out.println("location.href=login;"); out.println("</script>"); out.flush();
		  response.flushBuffer();
		  System.out.println("!!!!!!!!!!!!!!! 비정상적인 사용자 감지 아이피 : " +
		  request.getRemoteAddr());
		  System.out.println("!!!!!!!!!!!!!!! 사유 : NULL로 접근");
		 
		 return "login"; // alert후 로그인창으로 보내버릴거임
		  }

		// System.out.println("글쓰기 옵션 " + boardmapper.WriteContent(board));
		// 이거 띄웠는데 계속 500 error뜨고 있었네 ㅡㅡ;

		return "redirect:board"; // 리다이렉트 처리

	}

	@RequestMapping(value = "/commentwrite", method = RequestMethod.POST)
	public String commentwrite(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		System.out.println("댓글 작성 호출");
		int board_num = Integer.parseInt(request.getParameter("board_id"));
		System.out.println("댓글쓰기 board_num번호 : " + board_num);

		// Board boardDb = boardmapper.Content(board_num); // 객체타입으로 넣음

		Map map = new HashMap();
		request.setCharacterEncoding("UTF-8");
		String commentcontent = request.getParameter("commentcontent");
		System.out.println("댓글 작성 내용 : " + commentcontent);

		String userid = request.getParameter("comment_userid");
		System.out.println("댓글 전달받은 userid : " + userid);
		map.put("userid", userid); // 아직 널 값임
		map.put("comment_content", commentcontent);
		map.put("board_id", board_num);

		// map.put("content", content);
		boardmapper.WriteComment(map); // 댓글 등록

		// model.addAttribute("boardcontent", boardDb); // 객체로 값 넣음
		return "redirect:boardcontent?board_id=" + board_num;

	}
	
	
	@RequestMapping(value = "/backup", method = RequestMethod.GET)
	public String boardbackup(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		System.out.println("보드 백업 기능 호출");


		List list = null;
		list = boardmapper.BackUpData(board);
		model.addAttribute("boardbackup", list); // 값 넣음


		
		System.out.println("백업할 리스트 개수 : " + list.size());
		// model.addAttribute("boardcontent", boardDb); // 객체로 값 넣음
		return "backup";

	}

	
	/**
	 *****************************************************************/
	@RequestMapping(value = "/search", method = RequestMethod.GET) // value = "search", required = false
	@ResponseBody
	public ModelAndView   view(@RequestParam (defaultValue = "search") String search,
	Model model, HttpServletRequest request , Board board) throws Exception {
		System.out.println("search 호출");

		Map map = new HashMap();
		List list = boardmapper.SearchContentList(search);
		org.json.JSONObject obj = new org.json.JSONObject();
		ModelAndView mv = new ModelAndView();
	try {
		 System.out.println("검색내용 requestParam : " + search);		
		 
		
			System.out.println("검색 결과 내용 사이즈 : " + list.size());
			map.put("searchlist", list);
			map.put("search", search);
			
			obj.put("searchlistadd", list);
			mv.addObject("result", list);
			mv.setViewName("board");
			System.out.println("json obj : " + obj);

			

	} catch (Exception e) {
		System.out.println("검색기능 예외 발생 : " + e);
		System.out.println("예외 상태 map 들어간 값 : " + map);
		
	}

		 return mv ;

	}
	
	/*******************************************************************/
	
	@RequestMapping(value = "/test2", method = RequestMethod.GET)
	public String view2(@RequestParam (value = "search", required = false, defaultValue = "search") String search,
	Model model, HttpServletRequest request ) throws Exception {
	
System.out.println("test2 페이지 호출");

return "honme";
	}
}
