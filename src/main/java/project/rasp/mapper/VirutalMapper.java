package project.rasp.mapper;

import java.util.List;
import java.util.Map;

import project.rasp.model.Board;
import project.rasp.model.User;
import project.rasp.model.VirutalBoard;



public interface VirutalMapper {
	  public List<Board> getVirutalBoard(String virutal_name); // 가상게시판 게시글 로드
	  public void insertVirutalBoard(Map map); // 가상게시판 글쓰기 // 일단은 게시판 지정만 해놈 
	  													   // 유저정보는 좀 있다가
	  public List addBoardHeader(); // 가상게시판 헤더영역에 자동추가
	  public String virutal_available(String virutal_name); // 가상게시판 존재여부
	  public String checkVirutalBoardAuth(Map map); // 가상게시판 접근가능 여부
	  public String statusVirutalBoardAuth(String virutal_name); // 가상게시판 설정되어있는 권한확인
}
