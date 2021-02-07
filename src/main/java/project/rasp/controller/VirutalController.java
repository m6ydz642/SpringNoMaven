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

	 
	 public Boolean checkVirutalBoardAuth(String virutal_name, HttpSession session,
			 HttpServletRequest request, HttpServletResponse response) throws IOException { 
		 // 게시판 권한 + 유저현재 권한 검사상태 함수
		 
		 String user_auth = null;
	//	 String checkUserAuth = null;
		 String auth_message = null;
		 String checkBoardAuth = null;
		 int user_level = 0;
		 int check_level = 0;
		 boolean status = false;
		 Map map = new HashMap(); 
		 
		try {
			user_auth = (String) session.getAttribute("loginauth"); // 유저의 현재 권한상태 세션에서 가져옴 
			if (user_auth == null) user_auth="ANONYMOUS"; // 세션 null처리, 없으면 null pointer 익셉션 생김
			System.out.println("가상게시판 접근하는 현재유저 권한상태 : " + user_auth);
			
//			map.put("user_auth", user_auth);
//			map.put("virutal_name", virutal_name);
		//	checkUserAuth = virutalService.checkVirutalBoardAuth(map); // 접근하는 유저가 게시판에 권한이 있는지 검사
			if (user_auth.equals("ADMIN")) user_level = 4;
			if (user_auth.equals("MANAGER")) user_level = 3;
			if (user_auth.equals("SUPERUSER")) user_level = 2;
			if (user_auth.equals("USER")) user_level = 1;
			if (user_auth.equals("ANONYMOUS") ) user_level = 0; // 익명계정
			// 익명계정이면 DB상에 그냥 ""로 입력해놓으면 됨 (필드 입력안해서 null되는거 말고)
			
			System.out.println("현재 유저레벨 상태 : " + user_level);
		
			checkBoardAuth = virutalService.statusVirutalBoardAuth(virutal_name); 
			// 접근하는 유저가 접근하려는 게시판이
			// 무슨권한이 있는지를 안내해주기 위해 반환
			
			if (checkBoardAuth.equals("ADMIN")) check_level = 4;
			if (checkBoardAuth.equals("MANAGER")) check_level = 3;
			if (checkBoardAuth.equals("SUPERUSER")) check_level = 2;
			if (checkBoardAuth.equals("USER")) check_level = 1;
			if (checkBoardAuth.equals("ANONYMOUS")) check_level = 0;
			
			if (checkBoardAuth.equals("ADMIN")) auth_message = "관리자";
			if (checkBoardAuth.equals("MANAGER")) auth_message = "운영자";
			if (checkBoardAuth.equals("USER")) auth_message = "유저";
			if (checkBoardAuth.equals("SUPERUSER")) auth_message = "슈퍼유저";
			if (checkBoardAuth.equals("ANONYMOUS")) auth_message = "익명유저";
			
			System.out.println("현재 나의 권한 : " + user_level);
			System.out.println("접근하는 게시판 현재 권한 : " + check_level);
			System.out.println(user_level + " < " + check_level);
			System.out.println("유저레벨 감지 : " + user_level);
			if (user_level < check_level){
				String message = "귀하는 접근하실 수 없는 권한입니다 \\n"+ auth_message + "이상 권한이 필요합니다 ";
				status = false;
				custommessage.ErrorMessage(request, response, message); // null pointer 	예외처리 오류메시지 alert
			}else{
				status = true;
				System.out.println("가상게시판 유저가 접근가능함 status 상태 : " + status);
			
				
			}
			
			
	
		System.out.println("checkVirutal_Available : 가상 게시판 권한 체크완료");
		} catch (NullPointerException e) {
			System.out.println("virutalboardwrite.checkVirutalBoardAuth 게시판 접근 null감지 : " + e);
			String message = "게시판에 접근할 수 없습니다 \\n오류가 발생하였습니다";
			custommessage.ErrorMessage(request, response, message); // null pointer 	예외처리 오류메시지 alert
		}
		return status;
		
	}

	 
	 /**
	 * @throws IOException *****************************************************************************************************/
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
		
//		String result = null; 
//		result = checkVirutalBoard_Available(virutal_name, session, request, response);  // 게시판 존재여부
//		System.out.println("ttttttttttttttt : " + result);
//		if (!result.equals(null)) { // 게시판이 null이 아니고 권한만 없는거면 check함수 수행함 
//									// 이렇게 안하면 권한도 없고 게시판이 없는 게시판일때 오류가 2개 같이뜸
//		checkVirutalBoardAuth(virutal_name, session, request, response); // 권한조회 
//		}
//		
	checkVirutalBoard_Available(virutal_name, session, request, response); // 게시판 존재여부
	checkVirutalBoardAuth(virutal_name, session, request, response); // 권한조회 
		
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

//String result = checkVirutalBoard_Available(virutal_name, session, request, response);
//if (!result.equals(null)) {
//checkVirutalBoardAuth(virutal_name, session, request, response);
//}
		checkVirutalBoard_Available(virutal_name, session, request, response); // 게시판 존재여부
		checkVirutalBoardAuth(virutal_name, session, request, response); // 권한조회 
		return "virutal/virutalboardwrite";
	}
	
	
	
	@RequestMapping(value = "/virutalboardwritecomplete", method = RequestMethod.POST)
	public String completeVirutalWrite(@RequestParam String virutal_name, HttpSession session, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		
		String availableBoard = checkVirutalBoard_Available(virutal_name, session, request, response); // 게시판 존재여부
		boolean checkBoardAuth = checkVirutalBoardAuth(virutal_name, session, request, response); // 권한조회 
		
		System.out.println("가상 게시판 글작성 호출 완료 checkBoardAuth 상태 : " +checkBoardAuth );
		if (availableBoard != null && checkBoardAuth) { // 게시판이 존재하고 유저검사결과가 true라면, 즉 권한이 있다면
		System.out.println("가상 게시판 글작성 완료 호출");
		
		String subject = request.getParameter("subject");

		String content = request.getParameter("content");

		 String writer = request.getParameter("writer");
		 
		 //위에 메소드 인자에 @RequestParam이랑 getParameter랑 동일함 
		 // virutal_name은 virutalboardwrite에 input태그에 name값으로 hidden해서 가져옴
		 
		System.out.println("가상게시판 작성자 파라메터로 한번 받아와 봄 : " + writer);

		Map map = new HashMap();
		map.put("userid", writer.trim()); // 공백제거
		map.put("subject", subject);
		map.put("content", content);
		map.put("virutal_name", virutal_name);
		
		virutalService.insertVirutalBoard(map);
		
		System.out.println("--------------------------------------");
		System.out.println("가상게시판 게시글 작성자 : " + writer);
		System.out.println("가상 게시판 글제목 : " + subject);
		System.out.println("가상 게시판 내용 : " + content);
		System.out.println("가상 게시판 이름 : " + virutal_name); 
		System.out.println("--------------------------------------");
		}else{
			return "virutal?virutal_name"+virutal_name; //history back할시 return 리다리엑트 안되서 그냥 존재하는 다른주소로 넣음
			// 아무 값을 넣었더니 jsp를 못찾는 servlet Exception이 발생해서 존재하는 주소로 넣음
		}
	

		return "redirect:/virutal?virutal_name="+virutal_name; // 쓰던 가상게시판으로 리다이렉트 처리

	}
}
