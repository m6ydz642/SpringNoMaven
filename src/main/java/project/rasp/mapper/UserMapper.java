package project.rasp.mapper;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import project.rasp.model.Board;
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
	
	public List<Board> SearchContentList(@Param ("search")String search) throws Exception; // 검색결과 리스트

}
