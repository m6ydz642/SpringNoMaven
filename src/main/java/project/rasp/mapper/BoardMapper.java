package project.rasp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import project.rasp.model.Board;
import project.rasp.model.User;
import project.rasp.model.Comment;


public interface BoardMapper {

  public List<Board> getContentlist(Board board) throws Exception; // 일단은 테스트로 글번호만 가져와보기
  public Board Content(int board_id); // 보드
  public List<Comment> Comment(int board_num); // 댓글
  public void WriteContent(Map map); // 맵으로 글쓴 내용 가져감
  									 // 객체로 안됨 ㅠ 
  										
  public void WriteComment(Map map); // 댓글
}
