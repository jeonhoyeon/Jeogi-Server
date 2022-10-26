package com.howabout.there.mypage.dto;

public class UserDto {
	private String u_nick;
	private String u_id;
	private String u_pw;
	private String birth;
	private int gender;
	
	public UserDto(String u_nick, String u_id, String u_pw, String birth, int gender) {
		super();
		this.u_nick = u_nick;
		this.u_id = u_id;
		this.u_pw = u_pw;
		this.birth = birth;
		this.gender = gender;
	}
	
	public String getU_nick() {
		return u_nick;
	}
	public void setU_nick(String u_nick) {
		this.u_nick = u_nick;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getU_pw() {
		return u_pw;
	}
	public void setU_pw(String u_pw) {
		this.u_pw = u_pw;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	
	@Override
	public String toString() {
		return "UserDto [u_nick=" + u_nick + ", u_id=" + u_id + ", u_pw=" + u_pw + ", birth=" + birth + ", gender="
				+ gender + "]";
	}
	
	
	
}
