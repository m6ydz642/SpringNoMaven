package project.rasp.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.view.RedirectView;

import project.rasp.model.Board;
import project.rasp.service.AdminService;



/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminController{

		@Qualifier("adminServiceimpl")
		@Autowired
		 private AdminService uadminService;
		Model testmodel;
		Board board_id = new Board(); // 보드값 가리기를 위해 숨겨서 전달할 전역 객체
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@RequestMapping(value = "/admin/userlist", method = RequestMethod.GET)
	public String adminpage(Model model, HttpServletRequest request, HttpSession session) { 
		// 현재 접속자 유저 확인하려고 객체에 넣어돔
		// 아직은 확인작업 안함
		
		// 가상테이블 받아오기
		String test = (String) request.getAttribute("virutal_name");
		System.out.println("개체 전달테스트 : " + test);
		 logger.info("어드민 유저 페이지 호출"); // logger ㅅㅂ 안됨
		 logger.debug("테스트");
		 List testmapping = uadminService.addBoard();
		 session.setAttribute("virutal_name", testmapping); //게시판제목전용)
		// session.setAttribute("virutal_name", testmapping);
		 // 글에 들어갔을때 전용 string만들기
		 System.out.println("testmapping : " + testmapping);
		 List list = uadminService.userlistadmin();
		 model.addAttribute("adminuserlist", list);
		 System.out.println("어드민 유저페이지 호출  리스트 : " + list);
		System.out.println("어드민 페이지 호출 유저리스트 조회");
		return "admin/userlist";
	}


/**************************************************************/
// 어드민이 유저 auth변경하는 페이지
	
	@ResponseBody
	@RequestMapping(value = "/admin/adminauthchange", method = RequestMethod.POST)
	public String adminauthchange(Model model,
			@RequestParam String userid, @RequestParam String changebutton ) { 
		// 현재 접속자 유저 확인하려고 객체에 넣어돔
		// 아직은 확인작업 안함

		 logger.info("어드민 유저 권한변경 페이지 호출"); // logger ㅅㅂ 안됨
		 logger.debug("테스트");
		 // 어드민인지 아닌지 확인하는 과정 필요함
		 // 일단 생략 
		 System.out.println("어드민 유저권한변경 전달받은 아이디 : " + userid);
		 System.out.println("어드민 유저권한변경 전달받은 버튼 : " + changebutton);
		 
		 Map map = new HashMap();
		 org.json.JSONObject info = new org.json.JSONObject();
		 map.put("userid", userid); // 변경할 아이디
		 map.put("changebutton", changebutton); // 버튼변경값 
		 map.put("auth", changebutton); // 버튼변경값, 위에꺼랑 동일한데 키 때문에 한개 더 함
		 
		 int result = uadminService.adminauthchange(map);
			org.json.JSONObject obj = new org.json.JSONObject();
			 obj.put("authstatus", map);
			 
		System.out.println("어드민 페이지 권한 변경 결과 : " + result);
		System.out.println("어드민 페이지 권한 수정 결과 : " + obj);
		
		return obj.toString();
	}
	
	@RequestMapping(value = "/admin/adminboardmodify", method = RequestMethod.GET)
	public ModelAndView adminboardmodifywrite(Model model, @RequestParam int board_id) { 
		
		this.board_id.setBoard_id(board_id);
		RedirectView rv = new RedirectView("/admin/adminboardmodifywrite");
		rv.setExposeModelAttributes(false); // 주소 안보이게s

		return new ModelAndView(rv);
	}
	
	/**************************************************************/

	@RequestMapping(value = "/admin/adminboardmodifywrite", method = RequestMethod.GET)
	public String adminboardmodify(Model model, Board board) { 
		int board_id = this.board_id.getBoard_id();
		 System.out.println("어드민 수 board_id : " + board_id );
		 board  = uadminService.adminboardmodify(board_id);
		 model.addAttribute("adminboardmodifylist", board);
		 System.out.println("어드민 글수정 페이지 호출  리스트 : " + board_id);
		System.out.println("어드민 글수정 페이지 호출 유저리스트 조회 : " + board_id);
		System.out.println("수정할 글내용 : " + board);
		
		
		return "/admin/adminboardmodifywrite";
	}
	
	/**************************************************************/

	@RequestMapping(value = "/admin/adminboardlist", method = RequestMethod.GET)
	public String adminboardlist(Model model, Board board) { 
	
		 List list = uadminService.adminboardlist(board);
		 model.addAttribute("adminboardlist", list);
		 System.out.println("어드민 글조회 페이지 호출  리스트 : " + list);
		System.out.println("어드민 글조회 페이지 호출 유저글리스트 조회");
		return "/admin/adminboard";
	}
	
	/**************************************************************/

	@RequestMapping(value = "/admin/addboard", method = RequestMethod.GET)
	public String addBoard(Model model, Board board) { 
	
		 
		System.out.println("어드민 게시판 생성페이지 접근");
		return "/admin/addboard";
	}

	/**************************************************************/

	@RequestMapping(value = "/admin/addBoardComplete", method = RequestMethod.POST)
	public String addBoardComplete(Model model, @RequestParam String virutal_name, 
			 @RequestParam String virutal_auth, HttpServletRequest request, HttpSession session) { 
		System.out.println("가상테이블 이름 : " + virutal_name);
		System.out.println("가상테이블 권한 : " + virutal_auth);
	
		System.out.println("어드민 게시판 생성완료");
//		return "redirect:/board?curPage=1";
		return "redirect:/admin/userlist";
	}
	
	@RequestMapping(value = "/admin/{virutal_name}", method = RequestMethod.GET)
	public String test(Model model, HttpSession session,
		 HttpServletRequest request) { 

		System.out.println("가상 게시판 접근 완료");
		System.out.println("게시판 : ");
//		return "redirect:/board?curPage=1";
		List url = (List) session.getAttribute("virutal_name");
		System.out.println("접근 url주소 : " + url);
		return "/admin/virutal";
	}
	
	
}



