package project.rasp.service;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import project.rasp.mapper.UserMapper;
import project.rasp.mapper.Userimpl;
import project.rasp.model.User;

@Service
public class UserServiceimpl implements UserService {
	@Autowired
	@Qualifier("userimpl")
	private Userimpl userimpl;

	
	
	@Override
	public boolean UserLoginCheck(User user, HttpSession session) {
		System.out.println("UserLoginCheck 서비스 호출 : " + user);
		
		boolean result = userimpl.UserLoginCheck(user);
		if (result) {
		System.out.println("서비스 호출 정보 : " + user.getUserid());
		System.out.println("서비스 호출정보 2 : " + user.getUserpassword());
		
		}
		
		return result;
	}

}
