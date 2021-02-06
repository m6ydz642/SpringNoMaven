package project.rasp.service;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;

import project.rasp.model.Board;
import project.rasp.model.User;



public interface VirutalService {

	  public List<Board> getVirutalBoard(String virutal_name); // 가상게시판 게시글 로드
	  public void insertVirutalBoard(String virutal_name); // 가상게시판 글쓰기 // 일단은 게시판 지정만 해놈 
	  													   // 유저정보는 좀 있다가
	  public List addBoardHeader(); // 가상게시판 헤더영역에 자동추가
	  public String virutal_available(String virutal_name); // 가상게시판 존재여부
	  public String checkVirutalBoardAuth(Map map);

}
