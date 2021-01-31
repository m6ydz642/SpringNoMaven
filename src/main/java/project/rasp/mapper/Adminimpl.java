package project.rasp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import project.rasp.model.User;

public class Adminimpl implements AdminMapper{

	@Autowired
	SqlSession sqlsession;

	@Override
	public List<User> userlistadmin() {
		System.out.println("Adminimpl 유저조회" );
		List result = sqlsession.selectList("project.rasp.mapper.AdminMapper.userlistadmin"); 
		return  result;
	}

	@Override
	public int adminauthchange(Map map) {
		System.out.println("Adminimpl 유저권한변경 ");
		int result = sqlsession.update("project.rasp.mapper.AdminMapper.adminauthchange", map);
		return result;
	}
	
	


}
