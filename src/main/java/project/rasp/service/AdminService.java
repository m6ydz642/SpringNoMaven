package project.rasp.service;


import java.util.List;

import javax.servlet.http.HttpSession;

import project.rasp.model.User;



public interface AdminService {

	public List<User> userlistadmin (); // 유저 객체로 받아서 조회자가 나중에 어드민인지 확인할 용도
}
