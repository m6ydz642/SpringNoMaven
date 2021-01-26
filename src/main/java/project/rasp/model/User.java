package project.rasp.model;

import java.text.ParseException;




public class User {
	private int customer_id; // auto_increment로 걸리는 유저 번호임 
	// (계정 아이디 아님)
	private String userid ; // 계정 아이디 임
	private String userpassword ;
	private String username;
	private String[] write_date;
	private String auth;
	
	
	
	


	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
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
	

	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getUserid() {
		System.out.println("로그인 유저 아이디 게터 호출 : " + userid);
		return userid;
	}
	public void setUserid(String userid) {
		System.out.println("로그인 유저 아이디 세터 호출 : " + userid);
		this.userid = userid;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public String getUsername() {
		System.out.println("로그인 유저 이름 게터 호출 ");
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "User [customer_id=" + customer_id + ", userid=" + userid + ", userpassword=" + userpassword
				+ ", username=" + username + "]";
	}

	
	
}
