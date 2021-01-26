
package project.rasp.mapper;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.rasp.model.Board;
import project.rasp.model.User;

@Repository
public class Userimpl implements UserMapper {

	//@Inject
	@Autowired
	private SqlSession sqlsession;

	@Override
	public User UserLoginCheck(User user , HttpSession session) {
		User name; // printf로 출력 해볼려는데 객체타입이 String으로 안들어가서 
		// 객체 타입한개 더 선언해서 넣음띠
		String lock = null;
		name = sqlsession.selectOne("project.rasp.mapper.UserMapper.UserLoginCheck", user);
		System.out.println("sql UserMapper 세션 로그인 체크 내용 : " + name);
		return name;

	}

	@Override
	public List<Board> SearchContentList(String search) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	

}
