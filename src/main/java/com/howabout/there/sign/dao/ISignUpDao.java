package com.howabout.there.sign.dao;

import org.apache.ibatis.annotations.Mapper;

import com.howabout.there.sign.dto.SignUpDto;
import com.howabout.there.sign.vo.UserVo;

@Mapper
public interface ISignUpDao {
	
	//회원가입 user DB 등록 DAO
	public int signUp(SignUpDto userVo);
	//로그인 
	public int signIn(UserVo userDto);
	//회원가입 ID중복체크 DAO
	public boolean idCheck(String string);
	//회원가입 NICK중복체크 DAO
	public boolean nickCheck(String string);
		
}
