package com.howabout.there.sign.service;

import org.json.simple.parser.ParseException;

import com.howabout.there.sign.dto.SignUpDto;
import com.howabout.there.sign.vo.UserVo;

public interface ISignUpService {
	
	//DB아이디 등록 인터페이스
	public int signUp(SignUpDto signData) throws ParseException;
	
	//ID중복 체크 인터페이스
	public int idCheck(String signData) throws ParseException;

	//NICK중복 체크 인터페이스
	public int nickCheck(String signData) throws ParseException;
}
