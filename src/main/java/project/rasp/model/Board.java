package project.rasp.model;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;


public class Board { //. 게시판 리스트
	private int board_id;
	private String userid;
	private String username;
	private String subject;
	private String content;
	private int view_count;
	private String[] write_date;
	
	private String virutal_name; // virutal_name으로 변경해야 함
	
	



	public String getVirutal_name() {
		return virutal_name;
	}
	public void setVirutal_name(String virutal_name) {
		this.virutal_name = virutal_name;
	}
	public String[] getWrite_date() {
		return write_date;
	}
	public void setWrite_date(String write_date) throws ParseException {
		
		/*게터에서 애초부터 년,월,일로  잘라서 나가게 해놈 
		 * jstl태그로 안됨 ㅡ.ㅡ ;;; 
		 * 
		 * */
		String[] split = write_date.split(" ");
		this.write_date = split;
	}
	
	public int getBoard_id() {
		return board_id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Autowired
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getView_count() {
		return view_count;
	}
	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
	
	
}
