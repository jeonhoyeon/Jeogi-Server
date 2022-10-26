package com.howabout.there.sign.service;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.howabout.there.sign.dao.ISignUpDao;
import com.howabout.there.sign.dto.SignUpDto;

@Service
public class SignUpService implements ISignUpService{
	
	@Autowired
	ISignUpDao signdao;
	
	@Override
	@Transactional
	public int idCheck(String signData) throws ParseException {
		JSONParser parser = new JSONParser();        
		JSONObject jsonObject = (JSONObject)parser.parse(signData);
		
		try {
			boolean aa = signdao.idCheck(jsonObject.get("u_id").toString());
			if(aa == true) {
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
	public int nickCheck(String signData) throws ParseException {
		JSONParser parser = new JSONParser();        
		JSONObject jsonObject = (JSONObject)parser.parse(signData);
		
		try {
			boolean aa = signdao.nickCheck(jsonObject.get("u_nick").toString());
			if(aa == true) {
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
	public int signUp(SignUpDto signData) throws ParseException {
		System.out.println(Timestamp.valueOf(LocalDateTime.now()));
		System.out.println(LocalDateTime.now(ZoneId.of("Asia/Seoul")));
		System.out.println(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
		
		signData.setU_insert_id(signData.getU_id());
		signData.setU_insert_time(Timestamp.valueOf(LocalDateTime.now()));
		signData.setU_update_id(signData.getU_id());
		signData.setU_update_time(Timestamp.valueOf(LocalDateTime.now()));
		signData.setU_flag(1);
		try {
			signdao.signUp(signData);
			return 1;
		}  
		catch(Exception e) {
			return 0;
		}
	}
	

	
}
