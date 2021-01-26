package project.rasp.service;


import javax.servlet.http.HttpSession;

import project.rasp.model.User;



public interface UserService {

	/*
	 * public User getUserById(int id); public int add(User user); public int
	 * update(User user); public User getInformation();
	 * 
	 * @Delete("delete from users where id = #{id}") public int delete(@Param("id")
	 * int id);
	 */
	
	public User UserLoginCheck(User user, HttpSession session);

}
