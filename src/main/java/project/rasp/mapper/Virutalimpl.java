package project.rasp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import project.rasp.model.Board;
import project.rasp.model.User;
import project.rasp.model.VirutalBoard;

public class Virutalimpl implements VirutalMapper{

	@Autowired
	SqlSession sqlsession;


	public List addBoardHeader() {
		System.out.println("Virutalimpl addBoardHeader ");
		List result = sqlsession.selectList("project.rasp.mapper.VirutalMapper.addBoardHeader");
		return result;
	}

//	public int addBoardComplete(VirutalBoard virutalboard) {
//		int result = sqlsession.insert("project.rasp.mapper.VirutalMapper.addBoardComplete", virutalboard);
//		return result;
//	}

	@Override
	public List<Board> getVirutalBoard(String virutal_name) { // 보드의 정보를 가져옴
		List result = sqlsession.selectList("project.rasp.mapper.VirutalMapper.getVirutalBoard", virutal_name);
		return result;
	}

	@Override
	public void insertVirutalBoard(Map map) {
		int result = sqlsession.insert("project.rasp.mapper.VirutalMapper.insertVirutalBoard", map);
		System.out.println("project.rasp.mapper.VirutalMapper - insertVirutalBoard result : " + map);
	}

	@Override
	public String virutal_available(String virutal_name) {
		String result = sqlsession.selectOne("project.rasp.mapper.VirutalMapper.virutal_available", virutal_name);
		System.out.println("project.rasp.mapper.VirutalMapper - virutal_available result : " + virutal_name);
		return result;
	}

	public String checkVirutalBoardAuth(Map map) {
		String result = sqlsession.selectOne("project.rasp.mapper.VirutalMapper.checkVirutalBoardAuth", map);
		System.out.println("project.rasp.mapper.VirutalMapper - checkVirutalBoardAuth result : " + map);
		return result;
	}

	public String statusVirutalBoardAuth(String virutal_name) {
		String result = sqlsession.selectOne("project.rasp.mapper.VirutalMapper.statusVirutalBoardAuth", virutal_name);
		System.out.println("project.rasp.mapper.VirutalMapper - statusVirutalBoardAuth result : " + virutal_name);
		return result;
	}



	
	


}
