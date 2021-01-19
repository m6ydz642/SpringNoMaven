
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
		User name; // printf로 출력 해볼려는데 객체타입이 String으로 안들어가서 
		// 객체 타입한개 더 선언해서 넣음띠
		name = sqlsession.selectOne("project.rasp.mapper.UserMapper.UserLoginCheck", user);
		System.out.println("sql UserMapper 세션 로그인 체크 내용 : " + name);
		return (name == null) ? false : true;

	}

}
