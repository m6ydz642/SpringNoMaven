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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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

	/*
	 * @Autowired
	 * 
	 * @Qualifier("Comment")
	 */
	BoardMapper commemtmapper;

	UserMapper usermapper;
	private Comment comment;

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

		return "board"; // board.jsp로 이동
	}

	@RequestMapping(value = "/boardwrite", method = RequestMethod.GET)
	public String BoardWrite() {
		System.out.println("게시판 글작성 호출");

		// 조만간 세션 검사해서 아이디 확인여부 넣어야 됨

		return "boardwrite";

	}

	@RequestMapping(value = "/boarddelete", method = RequestMethod.GET)
	public String BoardDelete(HttpServletRequest request) {
		System.out.println("게시판 글삭제 호출");
		String Delete_Number = request.getParameter("board_id"); // content에 input태그에 name값을 계속씀
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("글삭제 호출 글 번호 : " + Delete_Number);
		// 조만간 세션 검사해서 아이디 확인여부 넣어야 됨
		// board_id값 받아야 됨
		boardmapper.DeleteContent(Integer.parseInt(Delete_Number)); // int로 전송

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
		logger.debug("시발");
		String Modify_Number_Write = request.getParameter("board_id"); // content에 input태그에 name값을 계속씀
		logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 와우로거");
		System.out.println("wow 페이지할 글 번호 : " + Modify_Number_Write);
		// 조만간 세션 검사해서 아이디 확인여부 넣어야 됨
		// board_id값 받아야 됨
		AnyRedirect("wowow"); // AnyRedirect에서 wowow로 보냈으면 주소창 숨김전환후
		// return으로 wowow.jsp를 찾아감
		return "wowow"; // 해당 jsp로 이동

	}

	/*********************************************************/
	@RequestMapping(value = "/boardmodifywrite", method = RequestMethod.GET) // post로 바꿔야 됨
	public String BoardModifyWrite(HttpServletRequest request, Model model) {
		System.out.println("게시판 수정할 페이지 호출");
		// String Modify_Number_Write = request.getParameter("board_id"); // content에
		// input태그에 name값을 계속씀
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
		// System.out.println("글수정 Request 글 번호 : " + Modify_Number_Write);
		// 조만간 세션 검사해서 아이디 확인여부 넣어야 됨
		// board_id값 받아야 됨
		// int board_num = Integer.parseInt(request.getParameter("board_id"));
		//

		int board_num = a.getBoard_id();
		System.out.println("a.getBoard_id = " + a.getBoard_id());
		Board boardDb = boardmapper.Content(board_num); // 객체타입으로 넣음

		model.addAttribute("boardcontent", boardDb); // 객체로 값 넣음

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

		String writer = (String) session.getAttribute("writer");
		// 사용자 세션 아직 null임 로그인 기능 안해서
		String subject = request.getParameter("subject");

		String content = request.getParameter("content");

		// 글수정
		Map map = new HashMap();
		// map.put("writer", writer); // 작성자 아직 널 값임
		map.put("subject", subject);
		map.put("content", content);
		boardmapper.WriteContent(map);

		System.out.println("--------------------------------------");
		System.out.println("게시글 작성자 : " + writer);
		System.out.println("글제목 : " + subject);
		System.out.println("내용 : " + content);
		System.out.println("--------------------------------------");

		PrintWriter out = response.getWriter();

		/*
		 * if (writer == null) { // 로그인 안한 사용자가 접근시
		 * 
		 * out.println("<script language='javascript'> ");
		 * out.println("alert('로그인부터 하세요 ^^;');"); //
		 * out.println("location.href=login;"); out.println("</script>"); out.flush();
		 * response.flushBuffer();
		 * System.out.println("!!!!!!!!!!!!!!! 비정상적인 사용자 감지 아이피 : " +
		 * request.getRemoteAddr());
		 * System.out.println("!!!!!!!!!!!!!!! 사유 : NULL로 접근");
		 * 
		 * return "request"; // alert후 로그인창으로 보내버릴거임 }
		 */

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

		// String username = request.getParameter("commentcontent");

		// map.put("writer", writer); // 아직 널 값임
		map.put("comment_content", commentcontent);
		map.put("board_id", board_num);

		// map.put("content", content);
		boardmapper.WriteComment(map); // 댓글 등록

		// model.addAttribute("boardcontent", boardDb); // 객체로 값 넣음
		return "redirect:boardcontent?board_id=" + board_num;

	}
	/*
	 * @RequestMapping(value = "/loginfail", method = RequestMethod.GET) public
	 * String loginfail(HttpServletRequest request) {
	 * System.out.println("로그인 실패 호출");
	 * 
	 * 
	 * System.out.println("!!!!!!!!!!!!!!! 비정상적인 사용자 감지 아이피 : " +
	 * request.getRemoteAddr()); System.out.println("!!!!!!!!!!!!!!! 사유 : 로그인 실패");
	 * return "loginerror";
	 * 
	 * }
	 * 
	 * 
	 * @RequestMapping(value = "/login", method = RequestMethod.GET) public String
	 * login(HttpServletRequest request) { System.out.println("로그인 호출");
	 * 
	 * 
	 * 
	 * System.out.println("!!!!!!!!!!!!!!! 비정상적인 사용자 감지 아이피 : " +
	 * request.getRemoteAddr()); System.out.println("!!!!!!!!!!!!!!! 사유 : 로그인 실패");
	 * 
	 * 
	 * 
	 * 
	 * return "login";
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/LoginCheck", method = RequestMethod.POST) public
	 * String loginSuccess(HttpServletRequest request, HttpSession session) {
	 * 
	 * String userID = request.getParameter("userID"); String userPassword =
	 * request.getParameter("userPassword");
	 * 
	 * System.out.println("로그인 성공");
	 * 
	 * Boolean result = usermapper.UserLogin(); System.out.println("로그인 성공 여부 : " +
	 * result);
	 * 
	 * System.out.println("입력받은 아이디 : " + userID); System.out.println("입력받은 비밀번호 : "
	 * + userPassword); System.out.println("접속요청 아이피 : " + request.getRemoteAddr());
	 * 
	 * System.out.println("!!!!!!!!!!!!!!! 비정상적인 사용자 감지 아이피 : " +
	 * request.getRemoteAddr()); System.out.println("!!!!!!!!!!!!!!! 사유 : 로그인 실패");
	 * 
	 * return "redirect:board"; // 로그인 성공시 보드로
	 * 
	 * }
	 */

}
