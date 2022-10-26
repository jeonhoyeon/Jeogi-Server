package com.howabout.there.mypage.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.howabout.there.mypage.dto.UserDto;
import com.howabout.there.mypage.service.MyPageService;
import com.howabout.there.sign.dto.SignUpDto;
import com.howabout.there.sign.vo.UserVo;

@RestController
@RequestMapping("/myPage/myInfo")
public class MyPageRController {
	
	@Autowired
	MyPageService myPageService;
	
	//유저 정보 업데이트. 회원정보 수정
	@PostMapping("/updateInfo")
	public UserDto updateUser(@RequestBody UserVo uservo){
		UserDto user = myPageService.userUpdate(uservo);
		return user;
	}
	
	//회원탈퇴. 비밀번호 확인 -> 비밀번호가 일치하면 1 반환. 서버에서 flag 0으로 업데이트
	@PostMapping("/withdrawal")
	public int withdrawl(@RequestBody ArrayList<JSONObject> data) throws ParseException {
		System.out.println("CHECK112233");
		int result = myPageService.withdrawal(data);
		return result;
	}
	
	//비밀번호 확인. 비밀번호 일치하면 1, 불일치하면 0 반환.
	@PostMapping("/CheckPW")
	public int checkPW(@RequestBody ArrayList<JSONObject> data) throws ParseException {
		int result = myPageService.pwCheck(data);
		return result;
	}
	
	//유저정보 가지고 오기
	@PostMapping("/getMyData")
	public UserDto userUp(@RequestBody ArrayList<JSONObject> myData){
		System.out.println("TEST CHECK ID"+ myData.toString());
		UserDto userUp = myPageService.userListUp(myData);
		
		return userUp;
	}
	
	

	
}
