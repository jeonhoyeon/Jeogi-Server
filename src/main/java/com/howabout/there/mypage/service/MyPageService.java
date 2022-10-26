package com.howabout.there.mypage.service;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howabout.there.mypage.dao.IMyPageDao;
import com.howabout.there.mypage.dto.UserDto;
import com.howabout.there.sign.vo.UserVo;

@Service
public class MyPageService implements IMyPageService{
	
	@Autowired
	IMyPageDao dao;
	
	//회원정보 수정
	public UserDto userUpdate(UserVo uservo){
		dao.updateUser(uservo);
		UserDto user = dao.selectUser(uservo.getU_id());
		return user;
	}
	
	//회원 탈퇴
	public int withdrawal(ArrayList<JSONObject> json) throws ParseException {
		JSONObject jsonObjec = (JSONObject) json.get(0);
		String id = jsonObjec.get("u_id").toString();
		String input_pw = jsonObjec.get("u_pw").toString();
		String withdrawalReason = jsonObjec.get("reason").toString();
		int result = dao.pwCheck(id, input_pw);
		
		//pw가 일치하면
		if(result == 1) {
			dao.withdrawal(id);
			dao.reason(withdrawalReason);
		}
		return result;
	}
	
	//비밀번호 확인
	public int pwCheck(ArrayList<JSONObject> json) throws ParseException {
		JSONObject jsonObjec = (JSONObject) json.get(0);
		String id = jsonObjec.get("u_id").toString();
		String input_pw = jsonObjec.get("u_pw").toString();
		int result = dao.pwCheck(id, input_pw);
		return result;
	}
	
	//회원정보 가지고 오기
	public UserDto userListUp(ArrayList<JSONObject> arrayjson) {
		String id = arrayjson.get(0).get("u_id").toString();
		UserDto myInfo = dao.selectUser(id);
		return myInfo;
	}
	
	
	
	
	
}
