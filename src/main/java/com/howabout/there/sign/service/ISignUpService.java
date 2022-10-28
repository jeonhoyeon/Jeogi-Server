package com.howabout.there.sign.service;

import java.util.Map;

import org.json.simple.parser.ParseException;

import com.howabout.there.sign.dto.SignUpDto;

public interface ISignUpService {
	
	//DB아이디 등록 인터페이스
	public int signUp(SignUpDto signData) throws ParseException;
	
	//ID중복 체크 인터페이스
	public int idCheck(Map signData) throws ParseException;

	//NICK중복 체크 인터페이스
	public int nickCheck(Map signData) throws ParseException;
}
