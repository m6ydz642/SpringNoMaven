
package project.rasp.mapper;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.rasp.model.User;

@Repository
public class Userimpl implements UserMapper {

	//@Inject
	@Autowired
	private SqlSession sqlsession;

	@Override
	public boolean UserLoginCheck(User user) {
		String name = sqlsession.selectOne("project.rasp.mapper.UserMapper.UserLoginCheck", user);
		System.out.println("sql UserMapper 세션 로그인 체크 내용 : " + name);
		return (name == null) ? false : true;

	}

}
