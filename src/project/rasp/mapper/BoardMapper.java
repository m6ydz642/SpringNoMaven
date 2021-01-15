package project.rasp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import project.rasp.model.Board;
import project.rasp.model.User;
import project.rasp.model.Comment;


public interface BoardMapper {// 이게 사실 서비스나 마찬가지임
	// 인터페이스에서 구현한 후 BoardImpl에서 리턴으로 객체 생성하던지 리턴만 하던지함

  public List<Board> getContentlist(Board board) throws Exception; // 일단은 테스트로 글번호만 가져와보기
  public Board Content(int board_id); // 보드
  public List<Comment> Comment(int board_num); // 댓글
  public void WriteContent(Map map); // 맵으로 글쓴 내용 가져감
  									 // 객체로 안됨 ㅠ 
  										
  public void WriteComment(Map map); // 댓글
  public void DeleteContent(int board_id); // 글삭제, int로 들고가서 board_id만 mapper에서 사용
  public void UpdateContent(Map map); // 맵으로 수정한 내용 가져감
}
