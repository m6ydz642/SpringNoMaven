package project.rasp.model;

import java.text.ParseException;


public class Comment {

	private int board_id;
	private int comment_number;
	private String userid;
	private String username;
	private String comment_content;
	private String[] comment_date;
	
	
	


	public String[] getComment_date() {
		return comment_date;
	}
	public void setComment_date(String write_date) throws ParseException {
		
		/*게터에서 애초부터 년,월,일로  잘라서 나가게 해놈 
		 * jstl태그로 안됨 ㅡ.ㅡ ;;; 
		 * 
		 * */
		String[] split = write_date.split(" ");
		this.comment_date = split;
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
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public int getComment_number() {
		return comment_number;
	}
	public void setComment_number(int comment_number) {
		this.comment_number = comment_number;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	
	
	
}
