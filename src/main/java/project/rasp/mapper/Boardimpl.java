package project.rasp.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import project.rasp.model.Board;
import project.rasp.model.Paging;

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

	@Override
	public List<Board> getContentMorelist(int count, int numberOfRequests) throws Exception {
		 numberOfRequests = numberOfRequests*5;
		return sqlsession.selectOne("project.rasp.mapper.BoardMapper.getContentMorelist", numberOfRequests);
	}

	@Override
	public int CommentUpdate(Map map) {
		int result = sqlsession.update("project.rasp.mapper.CommentUpdate", map);
		System.out.println("댓글 수정 결과 : " + result);
		// 사실 이거 서비스 연결안해놔서 안되는데 왜했지 ㅋㅋ
		return result;
	}



	@Override
	public int CommentDelete(Map map) {
		// TODO Auto-generated method stub
		return 0;
	}


}
