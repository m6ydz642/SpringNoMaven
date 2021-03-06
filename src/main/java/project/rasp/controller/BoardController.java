package project.rasp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import project.rasp.Customfunction;
import project.rasp.mapper.BoardMapper;
import project.rasp.mapper.UserMapper;
import project.rasp.mapper.VirutalMapper;
import project.rasp.model.Board;
import project.rasp.model.Comment;
import project.rasp.model.Paging;
import project.rasp.model.VirutalBoard;
import project.rasp.service.VirutalService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BoardController{

	 @Autowired
	BoardMapper boardmapper;
	private Board board;

	@Autowired
	VirutalService virutalservice;
	
	BoardMapper commemtmapper;

	UserMapper usermapper;

	
	private Customfunction customfunction = new Customfunction(); 
	// Customfunction customfunction; // 클래스에서 빈 주입 해놓음
	// 빈주입 할줄 몰라서 객체생성해서 씀 ^^ㅋㅋㅋㅋㅋㅋㅋ

	Board a = new Board(); // 일단 스프링 의존주입 같은거 몰라서 static으로 함
	// 다른 함수에서 다시 객체생성하는 순간 주소가 달라서 초기화 됨
	// 나중에 값 중복이나 다른곳에 있는 자료가 뜨면 여길 보면 됨

	// 아마 스프링은 원래 Service 만들어서 return해야 됨
	// 객체로 만들어도 상관은 없지만 스프링은 그걸 써서
	
	VirutalBoard virutal_name = new VirutalBoard(); // 전역으로 이름 가지고 다니게 선언

    @Autowired
    private ServletContext application; 



	Paging count = new Paging();
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	int countnum = 5; // boardmapper.xml에서 이미 초기 조회시 limit 0,5로 고정이
	// 되어있기 때문에 또 0으로 하면 중복으로 더보기가 되서 5로 고정
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

	public void viewVirutal_board(HttpServletRequest request) {
		 List testmapping = virutalservice.addBoardHeader(); // 가상테이블 select
		// session.setAttribute("virutal_name", testmapping); //게시판제목전용)
		 request.setAttribute("virutal_name", testmapping);
	}
	
	 
	/*********************************************************/
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String getBoardlist(Model model, HttpSession session, HttpServletRequest  request) throws Exception {
		logger.info("게시판 호출");
		countnum = 0; // 카운트를 전역 변수로 했기 때문에 
		// 새로고침을 해도 값 초기화가 안됨 그래서 여기서 지워버림 ^^;;
		List list = null;
		list = boardmapper.getContentlist(board);
		model.addAttribute("contentlist", list); // 값 넣음
		System.out.println("들어간 list 갯수 : " + list.size());
		 List testmapping = virutalservice.addBoardHeader(); // 가상테이블 select
		 
		 System.out.println("testmapping : " + testmapping);
		 application.setAttribute("num4", testmapping); //num4를 application으로 넣어다님
		return "board"; // board.jsp로 이동
	}

	@RequestMapping(value = "/boardwrite", method = RequestMethod.GET)
	public String witeBoard(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {
		System.out.println("게시판 글작성 호출");

	
		// 객체 주입해서 스프링 방식으로 진행 하려고 했는데
		// 잘 안되서 일단 객체생성으로 함 ㅠㅠ
		String value = customfunction.MemberCheck(session, request, response);
		
		System.out.println("보드 글작성 value값  확인 : " + value);
		// return "boardwrite";
		return value;

	}

	@RequestMapping(value = "/boarddelete", method = RequestMethod.GET)
	public String deleteBoard(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws IOException {
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

	@RequestMapping(value = "/boardmodify", method = RequestMethod.GET)
	public ModelAndView getmodifyBoard(HttpServletRequest request) {
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
	/**
	 * @throws IOException *******************************************************/
	@RequestMapping(value = "/boardmodifywrite", method = RequestMethod.GET) // post로 바꿔야 됨
	public String modifywriteBoard(HttpServletRequest request, Model model, HttpSession session, HttpServletResponse response) throws IOException {
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
	public String completemodifyBoard(HttpServletRequest request, HttpSession session) {
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
	public String getBoardContent_getCommentContent(HttpServletRequest request, Model model) throws Exception {

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
	public String completewriteBoard(HttpSession session, HttpServletRequest request, HttpServletResponse response,
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
	public String writeComment(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
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
	@RequestMapping(value = "/search", method = RequestMethod.GET, 
			produces = "application/text; charset=utf8") // value = "search", required = false
	// RequestMapping안에 produces = "application/text; charset=utf8")넣을시 인코딩 같이
	@ResponseBody
	public String   view(@RequestParam (defaultValue = "search") String search,
	Model model, HttpServletRequest request ,HttpServletResponse response ) throws Exception {
		System.out.println("search 호출");
		System.out.println("검색내용 requestParam : " + search);	
		Map map = new HashMap();
		List jsonlist = new ArrayList();
		 List list = boardmapper.SearchContentList(search);

		org.json.JSONObject obj = new org.json.JSONObject();
		 map.put("search", search);
			System.out.println("map 내용 : " + map);

	try {
		 	
		 
		
			System.out.println("검색 결과 내용 사이즈 : " + list.size());
			
			
			obj.put("searchlist", list);
			jsonlist.add(list);
			System.out.println("json값 :  " + jsonlist);
	
	} catch (Exception e) {
		System.out.println("검색기능 예외 발생 : " + e);
	
		
	}
	System.out.println("obj.toString() : " + obj.toString());
		 return obj.toString() ; // 시발 이거 어이없네 ㅋㅋㅋㅋㅋㅋㅋ

	}
	
	/*******************************************************************/
	
	@RequestMapping(value = "/popup", method = RequestMethod.GET)
	public String popup(Model model, HttpServletRequest request ) throws Exception {
	
System.out.println("팝업 페이지 호출");

return "popup";
	}
	
	/**************************************************************************************/
	// 게시글 더보기
	@RequestMapping(value = "/morelist", method = RequestMethod.GET, 
			produces = "application/text; charset=utf8") // value = "search", required = false
	// RequestMapping안에 produces = "application/text; charset=utf8")넣을시 인코딩 같이
	@ResponseBody
	public String   MoreList(Model model, HttpServletRequest request ,HttpServletResponse response
				) 
			throws Exception {
		System.out.println("게시글 더보기 호출");

		int numberOfRequests = 5; // 무조건 limit 에서 ?, 5 로 고정
		// 5페이지만 고정
	
		countnum += 5; // ajax로 컨트롤러 호출 될때마다 5씩증가임
		// 전역변수라서 쌉가능, /board를 새로고침하거나 다른페이지에 
		// 갔다가 다시 보드로 오면 새로고침을 할 수 밖에 없기 때문에
		// /board 컨트롤러에서 0으로 초기화를 시켜버렸음
		System.out.println("카운트 넘버 : " + countnum);
		System.out.println("sql limit : " + countnum + ", " + numberOfRequests);
		
		List list = boardmapper.getContentMorelist(countnum, numberOfRequests);
		
		
		
		System.out.println("더보기에 들어간 list 갯수 : " + list.size());
		org.json.JSONObject obj = new org.json.JSONObject();
		obj.put("morelist", list);
		obj.put("countnum", countnum);
		System.out.println("더보기 버튼 ajax json : " + obj.toString());
		return obj.toString() ; // json으로 리턴할 예정

	}
	
	/**************************************************************************************/
	// 댓글 수정 요청시 기존 댓글 select해서 보여주기
	@ResponseBody
	@RequestMapping(value = "/commentedit", method = RequestMethod.POST, produces = "application/text; charset=utf8") // post로 바꿔야 됨
	public String getCommentEdit(HttpServletRequest request, HttpSession session, 
			@RequestParam int comment_number, Comment comment) {
		System.out.println("댓글수정 컨트롤러 호출");

		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");


		// int comment_number = Integer.parseInt(request.getParameter("comment_number")); // 댓글번호
		// int board_id = Integer.parseInt(request.getParameter("board_id")); // 보드 번호
		// String testarea = request.getParameter("testarea"); // 글 수정 내용
		String userid = (String) session.getAttribute("loginid"); // 세션 로그인
		// String userid = checkUserid();
		// System.out.println("댓글수정 - 내용 : " + testarea);
		 System.out.println("댓글 수정요청 댓글번호 : " + comment_number);
		// System.out.println("댓글 수정요청 글번호 : " + board_id);
		 System.out.println("요청자 아이디 : " + userid); // 이제 DB에 있는 사용자 아이디랑 비교해서 나가면 될듯
		 
		 
		 Map map = new HashMap();
		 org.json.JSONObject info = new org.json.JSONObject();
		 map.put("userid", userid); // 세션에 요청중인 사용자 아이디
		 map.put("comment_number", comment_number); // 댓글 번호
	//	 map.put("comment_content", comment.getComment_content()); // 댓글 내용
		 
		// map.put("comment_content", testarea); // 댓글 작성 내용
	//	 map.put("board_id", board_id); // 보드번호
		comment = boardmapper.CommentEditSelect(map); // map으로 업데이트 쿼리까지 전달
		 // if문으로 0일경우 본인거ㄱ 아니라는 메시지 출력해야 됨
			
		info.put("comment_number", comment_number);
		info.put("comment_content", comment.getComment_content());
		// info.put("commentmodify", map);


		System.out.println("댓글수정전 조회 요청  쿼리 결과 : " + comment.toString());


		System.out.println("댓글수정전 조회 map에 담긴 내용 : " + map);
		System.out.println("댓글수정전 조회 담긴 내용 : " + info.toString());
		return info.toString();


	}
	
	/**************************************************************************************/
	// 댓글 수정, 삭제 컨트롤러, if문으로 처리해서 버튼 둘중에 한개로 그냥 작업함 이라고 해놨는데 
	// ajax때문에 걸려서 그냥 나눔 ㅋ
	@ResponseBody
	@RequestMapping(value = "/commenteditcomplete", method = RequestMethod.POST) // post로 바꿔야 됨
	public String completeEditComment(HttpServletRequest request, HttpSession session, 
			@RequestParam int comment_number, @RequestParam String testarea) {
		System.out.println("댓글수정 컨트롤러 호출");

		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");


		// int comment_number = Integer.parseInt(request.getParameter("comment_number")); // 댓글번호
		// int board_id = Integer.parseInt(request.getParameter("board_id")); // 보드 번호
		// String testarea = request.getParameter("testarea"); // 글 수정 내용
		 String userid = (String) session.getAttribute("loginid"); // 세션 로그인
		 
		 System.out.println("댓글수정 - 내용 : " + testarea);
		 System.out.println("댓글 수정요청 댓글번호 : " + comment_number);
		// System.out.println("댓글 수정요청 글번호 : " + board_id);
		 System.out.println("요청자 아이디 : " + userid); // 이제 DB에 있는 사용자 아이디랑 비교해서 나가면 될듯
		 
		 
		 Map map = new HashMap();
		 org.json.JSONObject info = new org.json.JSONObject();
		 map.put("userid", userid); // 세션에 요청중인 사용자 아이디
		 map.put("comment_number", comment_number); // 댓글 번호
		 map.put("comment_content", testarea); // 댓글 작성 내용
	//	 map.put("board_id", board_id); // 보드번호
			int result = boardmapper.CommentUpdate(map); // map으로 업데이트 쿼리까지 전달
		 // if문으로 0일경우 본인거ㄱ 아니라는 메시지 출력해야 됨
			
		info.put("comment_number", comment_number);
		info.put("testarea", map);


		System.out.println("댓글 수정 쿼리 결과 : " + result);


		System.out.println("댓글 수정 map에 담긴 내용 : " + map);
		System.out.println("댓글 수정 담긴 내용 : " + info.toString());
		return info.toString();


	}
	
	
	
	/**************************************************************************************/
	
	
	// 댓글 수정, 삭제 컨트롤러, if문으로 처리해서 버튼 둘중에 한개로 그냥 작업함
		@ResponseBody
		@RequestMapping(value = "/commentdelete", method = RequestMethod.POST) // post로 바꿔야 됨
		public String deleteComment(HttpServletRequest request, HttpSession session, 
				@RequestParam int comment_number) {
			System.out.println("댓글삭자ㅔ컨트롤러 호출");

			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");


			 String userid = (String) session.getAttribute("loginid"); // 세션 로그인
			 
		
			 System.out.println("댓글 삭제요청 댓글번호 : " + comment_number);
			// System.out.println("댓글 수정요청 글번호 : " + board_id);
			 System.out.println("댓글 삭제 요청자 아이디 : " + userid); // 이제 DB에 있는 사용자 아이디랑 비교해서 나가면 될듯
			 
			 
			 Map map = new HashMap();
			 org.json.JSONObject info = new org.json.JSONObject();
			 map.put("userid", userid); // 세션에 요청중인 사용자 아이디
			 map.put("comment_number", comment_number); // 댓글 번호
		

				int result = boardmapper.CommentDelete(map); // map으로 업데이트 쿼리까지 전달
			 // if문으로 0일경우 본인거ㄱ 아니라는 메시지 출력해야 됨
				
			info.put("comment_number", comment_number);
			info.put("comment", map);


			System.out.println("댓글삭제 쿼리 결과 : " + result);


			System.out.println("댓글삭제 map에 담긴 내용 : " + map);
			System.out.println("댓글삭제 담긴 내용 : " + info.toString());
			return info.toString();


		}

	
	
	
		
}
