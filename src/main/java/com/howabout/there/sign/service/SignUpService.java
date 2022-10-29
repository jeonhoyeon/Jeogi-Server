package com.howabout.there.sign.service;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.howabout.there.sign.dao.ISignUpDao;
import com.howabout.there.sign.dto.SignUpDto;

@Service
public class SignUpService implements ISignUpService{
	
	@Autowired
	ISignUpDao signdao;
	
	JSONParser parser = new JSONParser();       
	
	@Override
	@Transactional
	public int idCheck(Map signData) throws ParseException {     
//		JSONObject jsonObject = (JSONObject)parser.parse(signData);
		
		try {
			boolean id = signdao.idCheck(signData.get("u_id").toString());
			if(id == true) {
				return 0;
			}else {
				return 1;
			}
		}  
		catch(Exception e) {
			return 2;
		}
	}
	
	@Override
	@Transactional
	public int nickCheck(Map signData) throws ParseException {       
//		JSONObject jsonObject = (JSONObject)parser.parse(signData);
		
		try {
			boolean nick = signdao.nickCheck(signData.get("u_nick").toString());
			if(nick == true) {
				return 0;
			}else {
				return 1;
			}
		}  
		catch(Exception e) {
			return 2;
		}
	}
	
	@Override
	@Transactional
	public int emailCheck(Map signData) throws ParseException {       
			int email = signdao.emailCheck(signData.get("u_email").toString());
		return email;
	}
	
	@Override
	@Transactional
	public int signUp(SignUpDto signData) throws ParseException {
		System.out.println(Timestamp.valueOf(LocalDateTime.now()));
		System.out.println(LocalDateTime.now(ZoneId.of("Asia/Seoul")));
		System.out.println(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String resultPw = encoder.encode(signData.getU_pw());
		
		signData.setU_pw(resultPw);
		signData.setU_insert_id(signData.getU_id());
		signData.setU_insert_time(Timestamp.valueOf(LocalDateTime.now()));
		signData.setU_update_id(signData.getU_id());
		signData.setU_update_time(Timestamp.valueOf(LocalDateTime.now()));
		signData.setU_flag(1);
		signData.setU_email("cys@moble.com");
		try {
			signdao.signUp(signData);
			return 1;
		}  
		catch(Exception e) {
			return 0;
		}
	}
	

	
}
