package com.howabout.there.sign.service;



import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howabout.there.sign.dao.ISignInDao;
import com.howabout.there.sign.vo.UserVo;


@Service
public class SignInService implements ISignInService {

	@Autowired
	ISignInDao signDao;
	
	@Override
	public UserVo loginCheck(String signInData) throws ParseException {
		JSONParser parser = new JSONParser();        
		JSONObject jsonObject = (JSONObject)parser.parse(signInData);
		
		String id = jsonObject.get("u_id").toString();  
		String pw = jsonObject.get("u_pw").toString();

		UserVo loginCheck;// = new UserVo();
		
		try {
		loginCheck = signDao.userData(id,pw);
		loginCheck.getU_id();

		}catch(Exception e) {
			loginCheck = new UserVo();
			loginCheck.setU_flag(3);
		}
		return loginCheck;
	}
	public String loginMsg(String signInData) throws ParseException {
		JSONParser parser = new JSONParser();        
		JSONObject jsonObject = (JSONObject)parser.parse(signInData);
		String id = jsonObject.get("u_id").toString();  
		String answer;
		int ans;
		try {
			ans = signDao.findError(id);
		}catch(Exception e) {
			ans = 2;
		}
		if(ans == 1) {
			answer = "비밀번호가 일치하지 않습니다.";
		}else if(ans == 0){
			answer = "존재하지 않는 회원정보 입니다";
		}else {
			answer = "서버 오류가 발생 하였습니다.";
		}
		return answer;
	}

	
	public String findMyId(ArrayList<JSONObject> idHint){
		JSONObject idhint = idHint.get(0);
		String getMyId = signDao.findMyId((String)idhint.get("u_nick"), (String)idhint.get("birth") );
		return getMyId;
	}
	public int infoCheck(ArrayList<JSONObject> pwHint){
		JSONObject pwhint = pwHint.get(0);
		int checkMyInfo = signDao.checkMyInfo((String)pwhint.get("u_id"), (String)pwhint.get("u_nick"), (String)pwhint.get("birth") );
		return checkMyInfo;
	}
	public int setNewPw(Map myNewPw) {
		int setMyPw = signDao.setMyPw((String)myNewPw.get("u_id" ),(String)myNewPw.get("u_pw"));
		return 1;
	}
	
}
