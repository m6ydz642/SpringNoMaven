package project.rasp.mapper;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;

import project.rasp.model.Board;
import project.rasp.model.User;



public interface AdminMapper {

	public List<User> userlistadmin (); // 유저 객체로 받아서 조회자가 나중에 어드민인지 확인할 용도

}
