package project.rasp.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import project.rasp.model.Board;
import project.rasp.model.Paging;
import project.rasp.model.User;

public class Adminimpl implements AdminMapper{

	@Autowired
	SqlSession sqlsession;

	@Override
	public List<User> userlistadmin() {
		// System.out.println("Adminimpl 유저조회 값 : " + user);
		List result = sqlsession.selectList("project.rasp.mapper.AdminMapper.userlistadmin"); 
		return  result;
	}
	
	


}
