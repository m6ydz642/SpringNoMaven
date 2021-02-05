package project.rasp.mapper;

import java.util.List;
import java.util.Map;

import project.rasp.model.Board;
import project.rasp.model.User;
import project.rasp.model.VirutalBoard;



public interface AdminMapper {

	public List<User> userlistadmin (); // 유저 객체로 받아서 조회자가 나중에 어드민인지 확인할 용도
	public int adminauthchange(Map map); // 유저정보로 어드민 여부 확인할예정
											// 어드민이 별도 로그인으로 받는게 아니기에

	public List adminboardlist(Board board); // 어드민이 유저들글 전체조회
	public Board adminboardmodify(int board_id); 
	public int addBoardComplete(VirutalBoard virutalboard); // 가상게시판 생성완료
}
