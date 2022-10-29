package com.howabout.there.sign.service;



import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.howabout.there.sign.dao.ISignInDao;
import com.howabout.there.sign.dto.LoginDto;
import com.howabout.there.sign.vo.UserVo;
import com.howabout.there.token.JWTUtil;


@Service
public class SignInService implements UserDetailsService  {

	@Autowired
	ISignInDao signDao;
	
	@Autowired
	private JWTUtil jwtutil;
	
	BCryptPasswordEncoder encoder = new  BCryptPasswordEncoder();
	//로그인 체크
	public LoginDto loginCheck(Map signInData) throws ParseException {
		String returnValue = null;
		

		UserVo loginCheck;// = new UserVo();
		
		LoginDto resultDto = new LoginDto();
		
		
		loginCheck = signDao.userData(signInData.get("u_id").toString());
		
		//값이 없으면 47번째 줄에서 오류가 나옵니다.
		if(loginCheck == null) {
			System.out.println("ID ERROR");
			resultDto.setMsg("존재하지 않는 회원정보 입니다");
			resultDto.setSuccess(0);
		}
		else if (! encoder.matches(signInData.get("u_pw").toString(), loginCheck.getU_pw())) {
			System.out.println("PW ERROR");
			resultDto.setMsg("비밀번호가 일치하지 않습니다.");
			resultDto.setSuccess(0);
		}else {
			returnValue = jwtutil.generateToken(loginCheck, loginCheck.getU_id());
			System.out.println("login Token : " + returnValue);
			resultDto.setToken(returnValue);
			resultDto.setMsg(loginCheck.getU_nick());
			resultDto.setSuccess(1);
		}
		System.out.println("TOKEN : " + returnValue);
		return resultDto;
	}
	
	

	
	public String findMyId(Map idHint){
		String getMyId = signDao.findMyId(idHint.get("u_email").toString());
		return getMyId;
	}
	
	public int infoCheck(Map pwmyInfo){
		int checkMyInfo = signDao.checkMyInfo((String)pwmyInfo.get("u_email"), (String)pwmyInfo.get("u_id") );
		return checkMyInfo;
	}
	public int setNewPw(Map myNewPw) {
		int setMyPw = signDao.setMyPw((String)myNewPw.get("u_id" ),encoder.encode((String)myNewPw.get("u_pw")));
		return 1;
	}

	
	//유저의 id 값을 받아서 UserVo를 가져온 다음 UserDetails에 맞춰서 값을 넣어준다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("여기는 왔는가 ?" + username);
		UserVo userVo = new UserVo();
		System.out.println("name CHECK   "+username);
		userVo = signDao.userData(username);
		System.out.println("get USER VO ? : " + userVo.getU_id() + " " + userVo.getU_pw());
	
		return 	new org.springframework.security.core.userdetails.User(userVo.getU_id(),userVo.getU_pw(), new ArrayList<>());
	}
	
}
