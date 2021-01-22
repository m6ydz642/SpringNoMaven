package project.rasp.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import project.rasp.model.Board;

public class Boardimpl implements BoardMapper{

	@Autowired
	SqlSession sqlsession;
	
	@Override
	public List<Board> getContentlist(Board board) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Board Content(int board_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<project.rasp.model.Comment> Comment(int board_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void WriteContent(Map map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void WriteComment(Map map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DeleteContent(int board_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void UpdateContent(Map map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean UpdateValidationContent(Map map) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean DeleteValidationContent(Map map) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void UpdateViewCount(int board_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int CommentCount(int board_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Board> BackUpData(Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Board> SearchContentList(String search) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("search", search);
		System.out.println("SearchContentList값 : " + map);
		return sqlsession.selectOne("project.rasp.mapper.BoardMapper.SearchContentList", map);
	}


}
