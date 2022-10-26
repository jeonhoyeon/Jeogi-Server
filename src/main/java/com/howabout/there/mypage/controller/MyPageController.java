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

	// 이게 진짜 myInfo 요청 web용
	@GetMapping("/myPage/myInfo/webData")
	public String webMyInfo(@RequestBody String myData, Model model) {
		System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
		ArrayList<JSONObject> aa = new ArrayList<>();
		JSONObject bb = new JSONObject();
		bb.put("u_id", myData);
		aa.add(bb);
		UserDto userUp = myPageService.userListUp(aa);
		model.addAttribute("myInfo", userUp);
		return "myInfo";
	}

	// 이게 진짜 myInfoSetting 요청 web용
	@GetMapping("/myPage/myInfo/webDatasetting")
	public String webMyInfoSetting(@RequestBody String myData, Model model) {
		ArrayList<JSONObject> aa = new ArrayList<>();
		JSONObject bb = new JSONObject();
		bb.put("u_id", myData);
		aa.add(bb);
		UserDto userUp = myPageService.userListUp(aa);
		model.addAttribute("myInfo", userUp);
		return "myInfoSetting";
	}

	// 회원탈퇴 홈페이지
	@GetMapping("/myCourse/myCourse/webwithdrawal")
	public String webWithDrawalSite() {
		return "withdrawal";
	}

}