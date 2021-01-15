package project.rasp.model;

public class User {
	private int customer_id; // auto_increment로 걸리는 유저 번호임 
	// (계정 아이디 아님)
	private String userid ; // 계정 아이디 임
	private String userpassword ;
	private String username;
	// 시간은 나중에 ㅎㅎ
	
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	
}
