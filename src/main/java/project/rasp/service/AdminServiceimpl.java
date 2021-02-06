package project.rasp.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import project.rasp.mapper.Adminimpl;
import project.rasp.model.Board;
import project.rasp.model.User;
import project.rasp.model.VirutalBoard;

@Service
public class AdminServiceimpl implements AdminService {
	@Autowired
	@Qualifier("adminimpl")
	private Adminimpl adminimpl;

	@Override
	public List<User> userlistadmin() {
		List list = adminimpl.userlistadmin();
		System.out.println("AdminServiceimpl.userlistadmin 결과 " + list);
		return list;
	}

	@Override
	public int adminauthchange(Map map) {
		int result = adminimpl.adminauthchange(map);
		System.out.println("AdminServiceimpl.adminauthchange 결과 " + result);
		return result;
	}

	@Override
	public List adminboardlist(Board board) {
		List result = adminimpl.adminboardlist(board);
		System.out.println("AdminServiceimpl.admsinauthchange 결과 " + result);
		return result;
	}

	@Override
	public Board adminboardmodify(int board_id) {
		Board result = adminimpl.adminboardmodify(board_id);
		System.out.println("AdminServiceimpl.adminusermodify 결과 " + result);
		return result;
	}

//	@Override
//	public List addBoard() {
//		List result = adminimpl.addBoard();
//		System.out.println("AdminServiceimpl.addBoard 결과 " + result);
//		return result;
//	}
	// 유저페이지에서 addboardheader로 바꿈

	@Override
	public int addBoardComplete(VirutalBoard virutalboard) { // 게시판 생성시작
		int result = adminimpl.addBoardComplete(virutalboard);
		System.out.println("AdminServiceimpl.addBoardComplete 결과 " + result);
		return result;
	}

	


}
