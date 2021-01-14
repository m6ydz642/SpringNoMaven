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
public class LoginController {

@Autowired
	UserMapper usermapper;
	

	

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
	public String loginSuccess(HttpServletRequest request, HttpSession session) {
		
		String userID = request.getParameter("userID");
		String userPassword = request.getParameter("userPassword");
		Boolean result  = usermapper.UserLoginCheck();
		
		if (result) {
		System.out.println("로그인 성공");
		
	
		System.out.println("로그인 성공 여부 : " + result);
				
		System.out.println("입력받은 아이디 : " + userID);
		System.out.println("입력받은 비밀번호 : " + userPassword);
		  System.out.println("접속요청 아이피 : " +  request.getRemoteAddr());
		  return "redirect:board"; // 로그인 성공시 보드로
		}else {
			 System.out.println("!!!!!!!!!!!!!!! 로그인 실패 사용자 아이피 : " + request.getRemoteAddr()); 
			 return "login";
		}
		
		

	}


}
