package com.howabout.there.sign.dto;

import java.util.List;

import com.howabout.there.sign.vo.UserVo;

public class LoginDto {
	
	private int success; 
	private String msg;			//SUCCESS // 아이디가존재하지않거나 //비번이 틀렸다. 
	private UserVo userVo;
//	private String token;
	
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public UserVo getUserVo() {
		return userVo;
	}
	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}
	
	
	
	
	

}
