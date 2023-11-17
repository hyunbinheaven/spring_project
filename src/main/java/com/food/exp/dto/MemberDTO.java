package com.food.exp.dto;

import org.apache.ibatis.type.Alias;

@Alias("MemberDTO")
public class MemberDTO {
	
	String user_email;
	String nickname;
	String pw;
	String user_name;
	String user_post;
	String user_addr1;
	String user_addr2;
	String user_phone;
	
	public MemberDTO() {}

	public MemberDTO(String user_email, String nickname, String pw, String user_name, String user_post,
			String user_addr1, String user_addr2, String user_phone) {
		super();
		this.user_email = user_email;
		this.nickname = nickname;
		this.pw = pw;
		this.user_name = user_name;
		this.user_post = user_post;
		this.user_addr1 = user_addr1;
		this.user_addr2 = user_addr2;
		this.user_phone = user_phone;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_post() {
		return user_post;
	}

	public void setUser_post(String user_post) {
		this.user_post = user_post;
	}

	public String getUser_addr1() {
		return user_addr1;
	}

	public void setUser_addr1(String user_addr1) {
		this.user_addr1 = user_addr1;
	}

	public String getUser_addr2() {
		return user_addr2;
	}

	public void setUser_addr2(String user_addr2) {
		this.user_addr2 = user_addr2;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	@Override
	public String toString() {
		return "MemberDTO [user_email=" + user_email + ", nickname=" + nickname + ", pw=" + pw + ", user_name="
				+ user_name + ", user_post=" + user_post + ", user_addr1=" + user_addr1 + ", user_addr2=" + user_addr2
				+ ", user_phone=" + user_phone + "]";
	}
	
	

}
