package project.rasp.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import project.rasp.mapper.Virutalimpl;
import project.rasp.model.Board;

@Service
public class VirutalServiceimpl implements VirutalService {
	@Autowired
	@Qualifier("virutalimpl")
	private Virutalimpl virutalimpl;

	@Override
	public List<Board> getVirutalBoard(String virutal_name) {

		List result = virutalimpl.getVirutalBoard(virutal_name);
		System.out.println("VirutalServiceimpl.getVirutalBoard : " + result);
		return result;
	}

	@Override
	public void insertVirutalBoard(Map map) {
		virutalimpl.insertVirutalBoard(map); 
		System.out.println("VirutalServiceimpl.insertVirutalBoard 호출");
		
	}

	@Override
	public List addBoardHeader() {
		List result = virutalimpl.addBoardHeader();
		System.out.println("VirutalServiceimpl.addBoardHeader : " + result);
		return result;
	}

	@Override
	public String virutal_available(String virutal_name) {
		String result = virutalimpl.virutal_available(virutal_name);
		System.out.println("VirutalServiceimpl.virutal_available : " + result);
		return result;
	}

	@Override
	public String checkVirutalBoardAuth(Map map) {
		String result = virutalimpl.checkVirutalBoardAuth(map);
		System.out.println("VirutalServiceimpl.checkVirutalBoardAuth : " + result);
		return result;
	}

	@Override
	public String statusVirutalBoardAuth(String virutal_name) {
		String result = virutalimpl.statusVirutalBoardAuth(virutal_name);
		System.out.println("VirutalServiceimpl.statusVirutalBoardAuth : " + result);
		return result;
	}

	@Override
	public int deleteVirutalContent(Map map) {
		int result = virutalimpl.deleteVirutalContent(map);
		System.out.println("VirutalServiceimpl.deleteVirutalContent : " + result);
		return result;
	}

	@Override
	public Board getVirutalContent(int board_num) {
	Board result = virutalimpl.getVirutalContent(board_num);
		return result;
	}
}
