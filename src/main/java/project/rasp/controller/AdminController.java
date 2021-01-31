package project.rasp.controller;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.rasp.mapper.AdminMapper;
import project.rasp.mapper.UserMapper;
import project.rasp.model.User;
import project.rasp.service.AdminService;
import project.rasp.service.UserService;



/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminController{
	
	/* @Autowired
	AdminMapper adminmapper;*/
		@Qualifier("adminServiceimpl")
		@Autowired
		 private AdminService uadminService;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@RequestMapping(value = "/admin/userlist", method = RequestMethod.GET)
	public String adminpage(Model model) { 
		// 현재 접속자 유저 확인하려고 객체에 넣어돔
		// 아직은 확인작업 안함

		 logger.info("어드민 유저 페이지 호출"); // logger ㅅㅂ 안됨
		 logger.debug("테스트");
		 List list = uadminService.userlistadmin();
		 model.addAttribute("adminuserlist", list);
		System.out.println("어드민 페이지 호출 유저리스트 조회");
		return "admin/userlist";
	}



	
	
	
	
}



