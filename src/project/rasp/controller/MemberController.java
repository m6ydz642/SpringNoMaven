package project.rasp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.rasp.model.User;
import project.rasp.service.UserService;

/**
 * Handles requests for the application home page.
 */
@ComponentScan
@Controller
public class MemberController {
	
	@Qualifier("userServiceimpl")
	@Autowired
	 private UserService userService;



	@RequestMapping(value = "/loginfail", method = RequestMethod.GET)
	public String loginfail(HttpServletRequest request) {
		System.out.println("로그인 실패 호출");
		
		
		  System.out.println("!!!!!!!!!!!!!!! 비정상적인 사용자 감지 아이피 : " +
				 request.getRemoteAddr());
			System.out.println("!!!!!!!!!!!!!!! 사유 : 로그인 실패");
		return "loginerror";

	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		System.out.println("로그인 호출");
		
		
		/*
		 * System.out.println("!!!!!!!!!!!!!!! 비정상적인 사용자 감지 아이피 : " +
		 * request.getRemoteAddr()); System.out.println("!!!!!!!!!!!!!!! 사유 : 로그인 실패");
		 */
				
		return "login"; // login.jsp로 이동

	}
	
	@RequestMapping(value = "/LoginCheck", method = RequestMethod.POST)
	public String LoginCheck(HttpServletRequest request, HttpSession session, @ModelAttribute User user) {

		/*스프링 프레임워크 로그인 참고 해볼곳
		 * 
		 * https://m.blog.naver.com/PostView.nhn?blogId=heartflow89&logNo=221108884368&proxyReferer=https:%2F%2Fwww.google.com%2F
		 * 검색은 "스프링 프레임워크 로그인" 이라고 해야됨 안그러면 계속
		 * 스프링 부트로 해서  나옴  
		 * 
		 * */

		try {
			System.out.println("모델 attrubute 로그인 값 : "+ user);
		//	System.out.println("usermapper 값 : " + usermapper.UserLoginCheck(user));
			boolean result = userService.UserLoginCheck(user, session);
			System.out.println("로그인 체크 컨트롤러 : " + result);

			// if (result) {
			System.out.println("로그인 호출");
		

		} catch (Exception e) {
			System.out.println("로그인 예외 발생 : " + e);
			 System.out.println("!!!!!!!!!!!!!!! 로그인 실패 사용자 아이피 : " + request.getRemoteAddr());
		}

		  System.out.println("접속요청 아이피 : " +  request.getRemoteAddr());
		 // return "redirect:board"; // 로그인 성공시 보드로
		/// }else {
			 
			 return "login";
		// }
		
		

	 }

	
	}
