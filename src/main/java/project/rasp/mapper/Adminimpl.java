package project.rasp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import project.rasp.model.Board;
import project.rasp.model.User;
import project.rasp.model.VirutalBoard;

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

	public List adminboardlist(Board board) {
		System.out.println("Adminimpl adminboardlist ");
		List result = sqlsession.selectList("project.rasp.mapper.AdminMapper.adminboardlist", board);
		return result;
	}

	public Board adminboardmodify(int board_id) {
		System.out.println("Adminimpl adminusermodify ");
		Board result = sqlsession.selectOne("project.rasp.mapper.AdminMapper.adminboardmodify", board_id);
		return result;
	}

	public List addBoard() {
		System.out.println("Adminimpl addBoard ");
		List result = sqlsession.selectList("project.rasp.mapper.AdminMapper.addBoard");
		return result;
	}

	public int addBoardComplete(VirutalBoard virutalboard) {
		int result = sqlsession.insert("project.rasp.mapper.AdminMapper.addBoardComplete", virutalboard);
		return result;
	}



	
	


}
