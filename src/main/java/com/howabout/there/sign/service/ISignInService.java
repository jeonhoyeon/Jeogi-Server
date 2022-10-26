package com.howabout.there.sign.service;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.howabout.there.sign.vo.UserVo;

public interface ISignInService {

	//아이디 비밀번호 비교 여부
	public UserVo loginCheck(String signInData) throws ParseException;
	// 
	public String loginMsg(String signInData) throws ParseException;
	
	public String findMyId(ArrayList<JSONObject> idHint);
	
	public int infoCheck(ArrayList<JSONObject> checkMyInfo);
}
