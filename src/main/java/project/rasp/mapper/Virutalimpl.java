package project.rasp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import project.rasp.model.Board;

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

	public int deleteVirutalContent(Map map) {
		// int result = sqlsession.delete("project.rasp.mapper.VirutalMapper.deleteVirutalContent", map);
		System.out.println("project.rasp.mapper.VirutalMapper - deleteVirutalContent result : " + map);
		int result=1234; // 아직 삭제 안함 일단 검증확인만
		return result;
	}

	public Board getVirutalContent(int board_num) {
		System.out.println("가상게시판 게시글 조회 임플리먼트 : " + board_num);
		Board result = sqlsession.selectOne("project.rasp.mapper.VirutalMapper.getVirutalContent", board_num);
		return result;
	}
	



	
	


}
