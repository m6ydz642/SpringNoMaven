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



	@RequestMapping(value = "/virutal", method = RequestMethod.GET)
	public String virutal(Model model, HttpSession session,
		 HttpServletRequest request, @RequestParam String virutal_name) { 
		// String virutal_name = this.virutal_name.getVirutal_name();
		
		System.out.println("가상 게시판 접근 완료");
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
		

		return "/virutal/virutalboard"; //jsp페이지
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

	
		
	

		try {

			String virutal_available = virutalService.virutal_available(virutal_name); // 게시판이 유효한 게시판인지 검사
			// selectOne이 결과값이 테이블 컬럼내용 그대로 나와서 숫자면 괜찮은데 문자면 오류남
		System.out.println("가상게시판 유효성 검사 결과 : " + virutal_available);
		System.out.println("가상 게시판  글쓰기  접근 완료");
		if (virutal_available.equals(null)) {
			System.out.println("virutalboardwrite 게시판 존재하지 않음 감지 : " + virutal_available);
		}
		} catch (Exception e) {
			System.out.println("게시판 접근 null감지 : " + e);
			custommessage.checkVirutalBoard(session, request, response); // 예외처리 오류메시지 alert
			e.printStackTrace();
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
