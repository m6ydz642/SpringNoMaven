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
		System.out.println("로그인 체크 호출 유저아이디 : " + user.getUserid());
		System.out.println("로그인 체크 호출 유저패스워드 :  " + user.getUserpassword());
		System.out.println("로그인 체크 호출 유저이름 : " + user.getUsername());
		
		}else {
			System.out.println("로그인 체크 실패 result 반환 : " + result);
		}
		
		return result;
	}

}
