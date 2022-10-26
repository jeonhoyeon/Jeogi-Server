package com.howabout.there.mypage.controller;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.howabout.there.mypage.dto.UserDto;
import com.howabout.there.mypage.service.MyPageService;

@Controller
public class MyPageController {
	
	@Autowired
	MyPageService myPageService;
	
	@GetMapping("/myCourse/myCourse/myCourse")
	public String testtest() {
		return "login";
	}
	
	//홈페이지 테스트 내정보페이지
	@GetMapping("/myPage/myInfo/testwebData")
	public String testwebMyInfo(Model model) {
		UserDto userDto = new UserDto("Tutic", "testman", "1234", "2022-10-07 18:28:37", 1);
		model.addAttribute("myInfo", userDto);
		return "myInfo";
	}
	//홈페이지 테스트 정보변경페이지
	@GetMapping("/testmyinfoset")
	public String dasdwadasdasd(Model model) {
		UserDto userDto = new UserDto("Tutic", "testman", "1234", "2022-10-07 18:28:37", 1);
		model.addAttribute("myInfo", userDto);
		return "myInfoSetting";
	}
	
	// 이게 진짜 myInfo 요청 web용
	@PostMapping("/myPage/myInfo/webData")
	public String webMyInfo(@RequestBody ArrayList<JSONObject> myData, Model model) {
		UserDto userUp = myPageService.userListUp(myData);	
		model.addAttribute("myInfo", userUp);
		return "myInfo";
	}
	// 이게 진짜 myInfoSetting 요청 web용
	@PostMapping("/myPage/myInfo/webDatasetting")
	public String webMyInfoSetting(@RequestBody ArrayList<JSONObject> myData, Model model) {
		UserDto userUp = myPageService.userListUp(myData);	
		model.addAttribute("myInfo", userUp);
		return "myInfoSetting";
	}
	// 회원탈퇴 홈페이지
	@GetMapping("/myCourse/myCourse/webwithdrawal")
	public String webWithDrawalSite() {
		return "withdrawal";
	}
		
		
	
}
