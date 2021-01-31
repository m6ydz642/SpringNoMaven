package project.rasp.service;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import project.rasp.mapper.Adminimpl;
import project.rasp.mapper.Userimpl;
import project.rasp.model.User;

@Service
public class AdminServiceimpl implements AdminService {
	@Autowired
	@Qualifier("adminimpl")
	private Adminimpl adminimpl;

	@Override
	public List<User> userlistadmin() {
		List list = adminimpl.userlistadmin();
		System.out.println("AdminServiceimpl.userlistadmin 결과 " + list);
		return list;
	}

	


}
