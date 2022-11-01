package com.howabout.there.mypage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.howabout.there.mypage.dao.IMyPageDao;
import com.howabout.there.mypage.dto.UserDto;
import com.howabout.there.sign.vo.UserVo;
import com.howabout.there.token.JWTUtil;

@Service
public class MyPageService implements IMyPageService{

	@Autowired
	IMyPageDao dao;

	BCryptPasswordEncoder encoder = new  BCryptPasswordEncoder();

	@Autowired
	JWTUtil util;
	//회원정보 수정
	public Map userUpdate(UserVo uservo, String userNick){
		int resultUpdate;
		String newToken="";
		if(uservo.getU_pw()==null) {
			System.out.println("pw is null @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			uservo.setU_pw("");
		}else {
			System.out.println("pw not null #######################################################################################################################");
			String newPw = encoder.encode(uservo.getU_pw());
			String queryPW = ", u_pw = \""+newPw+"\"";
			uservo.setU_pw(queryPW);
		}
		dao.changeWriter(uservo.getU_nick(), userNick);
		resultUpdate = dao.updateUser(uservo);
		Map returnValue = new HashMap<>();
		if(resultUpdate==1) {
			newToken = util.generateToken(uservo, uservo.getU_id());
			returnValue.put("jwt", newToken);
			returnValue.put("nick", uservo.getU_nick().toString());
		}else {
			returnValue.put("jwt", "NO");
		}
		return returnValue;
	}

	//회원 탈퇴
	public int withdrawal(UserDto userUp, JSONObject json) throws ParseException {
//      String id = json.get(0).get("u_id").toString();
//     JSONObject jsonObjec = (JSONObject) json.get(0);
//     String id = jsonObjec.get("u_id").toString();
//      String withdrawalReason = jsonObjec.get("reason").toString();
		String id = userUp.getU_id();
		String withdrawalReason = (String) json.get("reason");
		dao.withdrawal(id);
		dao.reason(withdrawalReason);
		return 1;
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
	public UserDto userListUp(String userId) {
		UserDto myInfo = dao.selectUser(userId);
		System.out.println("myInfo get : "+myInfo.getU_pw());
		return myInfo;
	}





}