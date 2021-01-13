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
	private Comment comment;


	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

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

		return "boardwrite";

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
		System.out.println("글버호 = " + boardDb.getBoard_id());
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
	public String writecomplete(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		System.out.println("게시글 작성 완료 호출");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		
		String writer = (String) session.getAttribute("writer"); 
		// 사용자 세션 아직 null임 로그인 기능 안해서
		String subject = request.getParameter("subject");
	
		String content = request.getParameter("content");
	
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
		
		Map map = new HashMap();
		// map.put("writer", writer); // 아직 널 값임
		map.put("subject", subject);
		map.put("content", content);
		 boardmapper.WriteContent(map);

		return "redirect:board"; // 리다이렉트 처리

	}

	@RequestMapping(value = "/commentwrite", method = RequestMethod.POST)
	public String commentwrite(Model model, HttpServletRequest request) {
		System.out.println("댓글 작성 호출");
		int board_num = Integer.parseInt(request.getParameter("board_id"));
		System.out.println("댓글쓰기 board_num번호 : " + board_num);

		// Board boardDb = boardmapper.Content(board_num); // 객체타입으로 넣음

		Map map = new HashMap();
		String commentcontent = request.getParameter("commentcontent");
		System.out.println("댓글 작성 내용 : " + commentcontent);
		
	//	String username = request.getParameter("commentcontent");
		
		// map.put("writer", writer); // 아직 널 값임
		map.put("comment_content", commentcontent);
		map.put("board_id", board_num);
		
		// map.put("content", content);
		 boardmapper.WriteComment(map); // 댓글 등록

	//	model.addAttribute("boardcontent", boardDb); // 객체로 값 넣음
		return "redirect:boardcontent?board_id="+board_num;

	}

	@RequestMapping(value = "/loginfail", method = RequestMethod.GET)
	public String loginfail(HttpServletRequest request) {
		System.out.println("로그인 실패 호출");
		
		
		  System.out.println("!!!!!!!!!!!!!!! 비정상적인 사용자 감지 아이피 : " +
				 request.getRemoteAddr());
			System.out.println("!!!!!!!!!!!!!!! 사유 : 로그인 실패");
		return "loginerror";

	}

}
