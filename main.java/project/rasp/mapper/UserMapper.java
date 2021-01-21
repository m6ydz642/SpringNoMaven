package project.rasp.mapper;

import javax.servlet.http.HttpSession;

import project.rasp.model.User;



public interface UserMapper {

	/*
	 * public User getUserById(int id); public int add(User user); public int
	 * update(User user); public User getInformation();
	 * 
	 * @Delete("delete from users where id = #{id}") public int delete(@Param("id")
	 * int id);
	 */
	
	public boolean UserLoginCheck(User user, HttpSession session);

}
