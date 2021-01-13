package project.rasp.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.rasp.mapper.BoardMapper;
import project.rasp.mapper.UserMapper;
import project.rasp.model.Board;
import project.rasp.model.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	UserMapper userMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("/list")
	public String view(Model model) {
		// 데이터만 설정이 가능
		model.addAttribute("id","wow");
		System.out.println("/list 리스트 호출");
		System.out.println("request.jsp로 안내함");
		return "request";
	}
	

	  // DB Test
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String test() {
		
		User user = new User();
	    user.setName("joon");
	    user.setPassword("1234");
		int cnt = userMapper.add(user);

	    User userAdded = userMapper.getUserById(user.getId());
	    
	    System.out.println(userAdded.getId()+"--"+userAdded.getName()+"--"+userAdded.getPassword());
	    
	    return "home";
	}
	
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public String select() {
	 System.out.println("select호출");
	 
	 User userlist = userMapper.getInformation();
	 System.out.println("select문 userlist 내용 \n userID : " + userlist.getId() +  " / " + 
	"userName : "	+  userlist.getName()+ " / " + "userPasswd : " +userlist.getPassword());
		return "select"; // select.jsp로 이동
		
	}

	
	
	
	
}



