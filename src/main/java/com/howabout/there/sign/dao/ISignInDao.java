package com.howabout.there.sign.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.howabout.there.sign.vo.UserVo;

@Mapper
public interface ISignInDao {

	//유저Data 가지고오기
	public UserVo userData(@Param("u_id")String u_id);
	//아이디가 존재하는지 확인하기
	public int findError(@Param("u_id")String u_id);
	//내 아이디 찾기
	public String findMyId(@Param("u_email")String u_nick);
	//비밀번호 찾기전 내정보 확인
	public int checkMyInfo(@Param("u_email")String u_email , @Param("u_id")String u_id );
	//비밀번호 재설정
	public int setMyPw(@Param("u_id")String myId , @Param("u_pw") String myNewPw);
}
