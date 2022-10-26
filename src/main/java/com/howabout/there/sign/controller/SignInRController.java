package com.howabout.there.sign.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.howabout.there.sign.dto.LoginDto;
import com.howabout.there.sign.service.SignInService;
import com.howabout.there.sign.vo.UserVo;
import com.howabout.there.test.mailTest;


@RestController
public class SignInRController {
	
	@Autowired
	SignInService signIn;
	
	
	//로그인 기능 컨트롤러
	@PostMapping("/login/signIn")
	public LoginDto loginCheck(@RequestBody String data) throws ParseException {
		LoginDto userDto = new LoginDto();
		UserVo userVo = signIn.loginCheck(data);
		userDto.setUserVo(userVo);
		if(userVo.getU_flag()==3){
			userDto.setSuccess(0);
			userDto.setMsg(signIn.loginMsg(data));
		}else {
			userDto.setSuccess(1);
			userDto.setMsg("SUCCESS");
		}
		return userDto;
	}
	
	//아이디 찾기
	@PostMapping("/login/findMyId")
	public Map<String,String> findMyId(@RequestBody ArrayList<JSONObject> idHint) {
		String myId = signIn.findMyId(idHint);
		Map returnId = new HashMap<>();
		returnId.put("u_id", myId);
		return returnId;
	}
	
	//비밀번호 재설정 내정보 확인
	@PostMapping("/login/checkMyInfo")
	public int beforeCheckMyInfo(@RequestBody ArrayList<JSONObject> pwHint) {
		int myinfoCheck = signIn.infoCheck(pwHint);
		return myinfoCheck;
	}
	//비밀번호 재설정
	@PostMapping("/login/setNewPw")
	public int setNewPw(@RequestBody Map myNewPw) {
		int newPwResult = signIn.setNewPw(myNewPw);
		return 1;
	}
	
	
	@PostMapping("/testmap")
	public int asdsad(@RequestBody Map tt) {
		System.out.println("TEST MAP : "+ tt.get("first")+ " "+tt.get("second"));
		return 1;
	}
	@GetMapping("/mailTest")
	public String asdsad() {
		mailTest aa = new mailTest();
		aa.adasdada();
		return "11";
	}
	//Map으로 서버에 보내기
	@GetMapping("/asendMap")
	public Map<String,String> asdsddqdqad() {
		Map<String,String> return1 = new HashMap <String, String>();
		return1.put("aa", "asdsad");
		return return1;	
	}
	
	
}