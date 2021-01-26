package project.rasp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



// @Repository
public class Customfunction { // 사용자 지정 함수 클래스
	
	
	/*
	 * @Autowired
	 * 
	 * @Qualifier("customfunction")
	 */
	HttpServletRequest request;
	HttpServletResponse response;
	
	public String MemberCheck(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 로그인 실패 전용 함수 호출 
		
		/*아이디 체크에 대한 if문 사용이 계속 중복되는거 같아 하나 만듦 */
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		Object check = session.getAttribute("logininfo");
		System.out.println("member check 변수 : " + check);
		
		if (check == null) {
		  out.println("<script language='javascript'> ");
		  out.println("alert('로그인부터 하세요 ^^;');"); //
		  // out.println("location.href=login;");
		  out.println("history.back();");
		  out.println("</script>"); 
		  out.flush();
		  response.flushBuffer();
		  System.out.println("!!!!!!!!!!!!!!! 비정상적인 사용자 감지 아이피 : " +
		  request.getRemoteAddr());
		  System.out.println("!!!!!!!!!!!!!!! 사유 : NULL로 접근");
		}else {
			return "boardwrite"; // null아니면, 즉 정상로그인이면 보드작성페이지로
		}
		
		return "login";
	}
	
	public String MemberDB_Check(HttpSession session, HttpServletRequest request,
	HttpServletResponse response) throws IOException { // 맴버의 세션 ID와 DB상의 ID가 맞는지 확인용
		
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// 삭제, 수정에서 사용할 예정
		String loginstatus = (String) session.getAttribute("loginid");
		// boolean check = false;
		String check = loginstatus;
		System.out.println("loginstatus : " + loginstatus);
		if (loginstatus == null) { // 널이면 if문 false처리
			  out.println("<script language='javascript'> ");
			  out.println("alert('로그인부터 하세요 ^^;');"); //
			  // out.println("location.href=login;");
			  out.println("history.back();");
			  out.println("</script>"); 
			  out.flush();
			  
			  response.flushBuffer();
			  System.out.println("!!!!!!!!!!!!!!! 글수정 비정상적인 사용자 감지 아이피 : " +
			  request.getRemoteAddr());
			  System.out.println("!!!!!!!!!!!!!!! 사유 : NULL로 접근");
			  return "login";
		}
		
		return check;
		
	}
	/**********************************************************/
	public boolean UserValidationFail(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, boolean check) throws IOException { // 맴버의 세션 ID와 DB상의 ID가 맞는지 확인용
				
				request.setCharacterEncoding("UTF-8");
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				// 삭제, 수정에서 사용할 예정
		
				System.out.println("loginstatus : " + check);
				if (check == false) { // 널이면 if문 false처리
					  out.println("<script language='javascript'> ");
					  out.println("alert('으~~~딜 남의 글을 손대려고 ^^;');"); //
					  // out.println("location.href=login;");
					  out.println("history.back();");
					  out.println("</script>"); 
					  out.flush();
					  
					  response.flushBuffer();
					  System.out.println("!!!!!!!!!!!!!!! 글수정 비정상적인 사용자 감지 아이피 : " +
					  request.getRemoteAddr());
					  System.out.println("!!!!!!!!!!!!!!! 사유 : NULL로 접근");
					  return check;
				}
				
				return false;
			}
	
	public String PasswordCheck(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 로그인 실패 전용 함수 호출 
		
		/*아이디 체크에 대한 if문 사용이 계속 중복되는거 같아 하나 만듦 */
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		Object check = session.getAttribute("logininfo");
		System.out.println("member check 변수 : " + check);
		
		if (check == null) {
		  out.println("<script language='javascript'> ");
		  out.println("alert('로그인부터 하세요 ^^;');"); //
		  // out.println("location.href=login;");
		  out.println("history.back();");
		  out.println("</script>"); 
		  out.flush();
		  response.flushBuffer();
		  System.out.println("!!!!!!!!!!!!!!! 비정상적인 사용자 감지 아이피 : " +
		  request.getRemoteAddr());
		  System.out.println("!!!!!!!!!!!!!!! 사유 : NULL로 접근");
		}else {
			return "mypage"; // null아니면, 즉 정상로그인이면 보드작성페이지로
		}
		
		return "login";
	}
}
