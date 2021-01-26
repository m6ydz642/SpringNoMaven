package project.rasp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		System.out.println("로그인 호출");
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println("로그인 요청 현재 세션 값 : " + session.getAttribute("logininfo"));
		Object value = session.getAttribute("logininfo"); 
		System.out.println("value 값 : " + value);
		
		if (value != null) {
			
			  out.println("<script language='javascript'> ");
			  out.println("alert('이미 로그인 되어있습니다 ^^;');"); //
			  out.println("location.href = 'board' </script>"); 
			  out.flush();
			  response.flushBuffer();
		}
				
		return "login"; // login.jsp로 이동

	}
	
	@RequestMapping(value = "/LoginCheck", method = RequestMethod.POST)
	public String LoginCheck(Model model, HttpServletRequest request,HttpServletResponse response, HttpSession session, @ModelAttribute User user) throws IOException {

		/*스프링 프레임워크 로그인 참고 해볼곳
		 * 
		 * https://m.blog.naver.com/PostView.nhn?blogId=heartflow89&logNo=221108884368&proxyReferer=https:%2F%2Fwww.google.com%2F
		 * 검색은 "스프링 프레임워크 로그인" 이라고 해야됨 안그러면 계속
		 * 스프링 부트로 해서  나옴  
		 * 
		 * */
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		try {
			System.out.println("모델 attrubute 로그인 값 : "+ user);
		//	System.out.println("usermapper 값 : " + usermapper.UserLoginCheck(user));
			User result = userService.UserLoginCheck(user, session);
			System.out.println("로그인 서비스 체크 값 : " + result);
			System.out.println("로그인 체크 컨트롤러 : " + result);
			System.out.println("로그인 호출");
			if (result != null) {
		
				System.out.println("로그인 성공!!!!!!!!!");
				System.out.println("로그인 유저 이름 : " + user.getUserid()); 
				
				session.setAttribute("logininfo", user); // 로그인 정보 세션 등록
				// 이거 비밀번호 까지 같이 가서객체타입으로 안쓰려고 했는데 생각해보니  
				// jsp에 이미 좀 키값 사용해놓은게 좀 많음 ㅋㅋ 
				
				session.setAttribute("loginid", user.getUserid()); // 로그인 아이디 등록
				
				System.out.println("로그인 세션등록 성공");
				System.out.println("세션 값 : " + session.getAttribute("logininfo"));
				
				return "redirect:board";
			}else {
				System.out.println("멤버 컨트롤러에서 로그인 실패 처리 : " + result);
				
	
				  out.println("<script language='javascript'> ");
				  out.println("alert('아이디 혹은 비밀번호가 틀렸습니다 ^^;');"); //
				  out.println("location.href=login;"); out.println("</script>"); 
				  out.flush();
				  response.flushBuffer();
				  System.out.println("!!!!!!!!!!!!!!! 로그인 실패 사용자 감지 아이피 : " +
				  request.getRemoteAddr());
				  System.out.println("!!!!!!!!!!!!!!! 사유 : NULL로 접근");
				  
			}
		} catch (Exception e) {
			System.out.println("로그인 예외 발생 : " + e);
			 System.out.println("!!!!!!!!!!!!!!! 로그인 예외발생 사용자 아이피 : " + request.getRemoteAddr());
		}

		  System.out.println("접속요청 아이피 : " +  request.getRemoteAddr());
			 
			 return "login";
		// }
		
		

	 }
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		System.out.println("로그아웃 호출");
		
//		
		 request.setCharacterEncoding("UTF-8");
	 response.setContentType("text/html; charset=UTF-8"); 
		 PrintWriter out = response.getWriter(); 
//		 System.out.println("로그아웃 요청 현재 세션 값 : " +
//		  session.getAttribute("logininfo")); 
//		 Object value = session.getAttribute("logininfo");
//		 System.out.println("value 값 : " + value);
//		  
//		  value = null;
//		 
//		  if (value == null) {
////		  
//		  out.println("<script language='javascript'> ");
//		  out.println("alert('잘가시게.........또르르르....;');"); //
//		  out.println("location.href = 'board' </script>");
////		  
//		  out.flush(); response.flushBuffer(); 
////		  }
//		 
		session.invalidate(); // 세션 제거
				
		return "redirect:board"; // login.jsp로 이동

	}
	
	}
