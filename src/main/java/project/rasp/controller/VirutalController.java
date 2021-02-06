package project.rasp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import project.rasp.Customfunction;
import project.rasp.mapper.VirutalMapper;
import project.rasp.model.Board;
import project.rasp.service.VirutalService;

@Controller
public class VirutalController {

	@Autowired
	VirutalService virutalService;
	
	 @Autowired
	    private ServletContext application; 
	 
	 Customfunction custommessage = new Customfunction();
//	@RequestMapping(value = "/virutal", method = RequestMethod.GET)
//	public ModelAndView getVirutalBoard(HttpServletRequest request, @RequestParam String virutal_name) {
//		// ModelAndView방식 쓰려고 형태 바꿈
//		System.out.println("가상 게시판 호출");
//		
//		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
//		System.out.println("가상 게시판 호출 이름 : " + virutal_name);
//
//		// redirect밑에 주소 안보이게 하려고 false로 해놔서 안나옴
//		RedirectView rv = new RedirectView("/virutalboard?virutal_name="+virutal_name);
//		rv.setExposeModelAttributes(false); // 주소 안보이게
//		this.virutal_name.setVirutal_name(virutal_name);// 세터로 파라미터 인자 전달
//		
//	//	System.out.println("setter로 입력된 virutal_name : " + this.virutal_name.getVirutal_name());
//		return new ModelAndView(rv);
//		// 이거 문제가 String타입이 아니라서 .jsp를 알아서 못찾음...
//		// web.xml설정 건드리던지 다른 함수로 다시 보내서
//		// String으로 반환을 시키던지 해야됨 일단은 ↓ 밑에 매핑으로 보냄
//	}

	 public String checkVirutalBoard_Available(String virutal_name, HttpSession session,
			 HttpServletRequest request, HttpServletResponse response) throws IOException { // 게시판 존재여부 검사함수
		 String virutal_available = null;
		
		try {

			 virutal_available = virutalService.virutal_available(virutal_name); // 게시판이 존재하는 게시판인지 검사
			// selectOne이 결과값이 테이블 컬럼내용 그대로 나와서 숫자면 괜찮은데 문자면 오류남
			
			if (virutal_available.equals(null)) {
				System.out.println("virutalboardwrite 게시판 존재하지 않음 감지 : " + virutal_available);
			}
			// String virutal_auth = virutalService.virutal_available(user_auth);
		
		System.out.println("checkVirutal_Available : 가상게시판 유효성 검사 결과 : " + virutal_available);
		System.out.println("checkVirutal_Available : 가상 게시판  체크완료");
		} catch (NullPointerException e) {
			
			System.out.println("virutalboardwrite.checkVirutal_Available 게시판 접근 null감지 : " + e);
			custommessage.checkVirutalBoard(session, request, response); // null pointer 	예외처리 오류메시지 alert
		}
		return virutal_available;
		
	}

	 
	 public String checkVirutalBoardAuth(String virutal_name, HttpSession session,
			 HttpServletRequest request, HttpServletResponse response) throws IOException { 
		 // 게시판 권한 + 유저현재 권한 검사상태 함수
		 
		 String user_auth = null;
		 String checkUserAuth = null;
		 String auth_message = null;
		 String checkBoardAuth = null;
		 
		 Map map = new HashMap(); 
		 
		try {
			user_auth = (String) session.getAttribute("loginauth"); // 유저의 현재 권한상태 세션에서 가져옴 
			System.out.println("가상게시판 접근하는 현재유저 권한상태 : " + user_auth);
			map.put("user_auth", user_auth);
			map.put("virutal_name", virutal_name);
			checkUserAuth = virutalService.checkVirutalBoardAuth(map); // 접근하는 유저가 게시판에 권한이 있는지 검사 
			checkBoardAuth = virutalService.statusVirutalBoardAuth(virutal_name); 
			// 접근하는 유저가 접근하려는 게시판이
			// 무슨권한이 있는지를 안내해주기 위해 반환
			
			 System.out.println("checkBoardAuth : " + checkBoardAuth);
			 
			if (checkBoardAuth.equals("ADMIN")) auth_message = "관리자";
			if (checkBoardAuth.equals("MANAGER")) auth_message = "운영자";
			if (checkBoardAuth.equals("USER")) auth_message = "유저";
			if (checkBoardAuth.equals("SUPERUSER")) auth_message = "슈퍼유저";
			
			System.out.println("checkUserAuth : " + checkUserAuth);
			
			// 게시판이 유저권한이랑 맞는지 확인
			// selectOne이 결과값이 테이블 컬럼내용 그대로 나와서 숫자면 괜찮은데 문자면 오류남
			
			if (checkUserAuth.equals(null)) {
				System.out.println("게시판 권한 없음 감지 : " + checkUserAuth);
			}

			// String virutal_auth = virutalService.virutal_available(user_auth);
		
		System.out.println("checkVirutal_Available : 가상게시판 권한 검사 결과 : " + checkUserAuth);
		System.out.println("checkVirutal_Available : 가상 게시판 권한 체크완료");
		} catch (NullPointerException e) {
			System.out.println("virutalboardwrite.checkVirutalBoardAuth 게시판 접근 null감지 : " + e);

			String message = "죄송합니다 귀하는 접근하실 수 없는 권한입니다 \\n"+ auth_message + "이상 권한이 필요합니다 ";
			custommessage.ErrorMessage(session, request, response, message); // null pointer 	예외처리 오류메시지 alert
		}
		return checkUserAuth;
		
	}

	 
	 /*******************************************************************************************************/
	@RequestMapping(value = "/virutal", method = RequestMethod.GET)
	public String virutal(Model model, HttpSession session,
		 HttpServletRequest request, HttpServletResponse response, @RequestParam String virutal_name) throws IOException { 
		
		System.out.println("가상 게시판 접근");
		System.out.println("게시판 : " + virutal_name);
		System.out.println("접근 url 파라메터: " + virutal_name);

	    List<Board> list = virutalService.getVirutalBoard(virutal_name); // 가상게시글의 정보를 받아옴
		
		System.out.println("가상게시판 리스트  virutal_name : " + list);
		/*******************************************************/
		// 가상게시판 메뉴 다시 생성 (주소 직접접근할 경우 대비)
		List testmapping = virutalService.addBoardHeader(); // 가상테이블 selects
		application.setAttribute("num4", testmapping); // 주소로 직접적으로 접근할 경우 다시 application에 넣음
		/*******************************************************/
		model.addAttribute("virutal_board", list ); // virutal.jsp로 갈경우 다시 모델에 넣음
		
		String result = null; 
		result = checkVirutalBoard_Available(virutal_name, session, request, response);  // 게시판 존재여부
		System.out.println("ttttttttttttttt : " + result);
		if (!result.equals(null)) { // 게시판이 null이 아니고 권한만 없는거면 check함수 수행함 
									// 이렇게 안하면 권한도 없고 게시판이 없는 게시판일때 오류가 2개 같이뜸
		checkVirutalBoardAuth(virutal_name, session, request, response); // 권한조회 
		}
		
//		checkVirutalBoard_Available(virutal_name, session, request, response); // 게시판 존재여부
//		checkVirutalBoardAuth(virutal_name, session, request, response); // 권한조회 
		
		return "/virutal/virutalboard"; //jsp 페이지 이동
	}
	/**
	 * @throws IOException *******************************************************************************/
	
	
//	@RequestMapping(value = "/virutalboardwrite", method = RequestMethod.GET)
//	public ModelAndView writeBoardWrite(HttpServletRequest request, @RequestParam String virutal_name) {
//		// ModelAndView방식 쓰려고 형태 바꿈
//		System.out.println("가상 게시판 글작성 호출");
//		
//		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
//		System.out.println("가상 게시판 글작성 이름 : " + virutal_name);
//
//		// redirect밑에 주소 안보이게 하려고 false로 해놔서 안나옴
//		RedirectView rv = new RedirectView("/virutalboardwriteurl?"+virutal_name);
//		rv.setExposeModelAttributes(false); // 주소 안보이게
//		this.virutal_name.setVirutal_name(virutal_name);// 세터로 파라미터 인자 전달
//		
//		System.out.println("setter로 입력된 virutal_name : " + this.virutal_name.getVirutal_name());
//		return new ModelAndView(rv);
//		// 이거 문제가 String타입이 아니라서 .jsp를 알아서 못찾음...
//		// web.xml설정 건드리던지 다른 함수로 다시 보내서
//		// String으로 반환을 시키던지 해야됨 일단은 ↓ 밑에 매핑으로 보냄
//	}
	
	
	@RequestMapping(value = "/virutalboardwrite", method = RequestMethod.GET)
	public String virutalboardwrite(Model model, HttpSession session, HttpServletResponse response,
		 HttpServletRequest request, @RequestParam String virutal_name) throws IOException { 

String result = checkVirutalBoard_Available(virutal_name, session, request, response);
if (!result.equals(null)) {
checkVirutalBoardAuth(virutal_name, session, request, response);
}
		 
		return "boardwrite"; // 가상전용 게시판으로 바꿔야 됨 
	}
	
	
	
	@RequestMapping(value = "/vrutalboardwritecomplete", method = RequestMethod.POST)
	public String completeVirutalWrite(@RequestParam String virutal_name, HttpSession session, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		System.out.println("가상 게시판 글작성 완료 호출");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		virutalService.insertVirutalBoard(virutal_name);
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
	//	virualmapper.WriteContent(map);
		// 아직 게시글 작성 쿼리 안함
		
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
}
