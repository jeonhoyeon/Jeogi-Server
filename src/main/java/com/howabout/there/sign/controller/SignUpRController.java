package com.howabout.there.sign.controller;

import java.util.Map;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.howabout.there.sign.dto.SignUpDto;
import com.howabout.there.sign.service.SignUpService;

@RestController
@RequestMapping("/login/signUp")
public class SignUpRController {

	@Autowired
	SignUpService signService;

	// 회원가입 아이디 체크 컨트롤러
	@PostMapping("/idCheck")
	public int idCheck(@RequestBody Map data) throws ParseException {
		int idchecked = signService.idCheck(data);
		return idchecked;
	}

	// 회원가입 닉네임 체크 컨트롤러
	@PostMapping("/nickCheck")
	public int nickCheck(@RequestBody Map data) throws ParseException {
		int nickchecked = signService.nickCheck(data);
		return nickchecked;
	}

	@PostMapping("/emailkCheck")
	public int emailCheck(@RequestBody Map data) throws ParseException {
		int nickchecked = 1;
		return nickchecked;
	}
	
	// 회원가입 user DB에 데이터 넣기
	@RequestMapping(method = RequestMethod.POST)
	public int signUp(@RequestBody SignUpDto data) throws ParseException {
		int insertData = signService.signUp(data);
		return insertData;
	}

}
