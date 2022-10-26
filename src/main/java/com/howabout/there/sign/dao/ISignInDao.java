package com.howabout.there.sign.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.howabout.there.sign.vo.UserVo;

@Mapper
public interface ISignInDao {

	
	public UserVo userData(@Param("u_id")String u_id,@Param("u_pw")String u_pw);
	
	public int findError(@Param("u_id")String u_id);
	//내 아이디 찾기
	public String findMyId(@Param("u_nick")String u_nick, @Param("birth")String birth );
	//비밀번호 찾기전 내정보 확인
	public int checkMyInfo(@Param("u_id")String u_id ,@Param("u_nick")String u_nick, @Param("birth")String birth );
	//비밀번호 재설정
	public int setMyPw(@Param("u_id")String myId , @Param("u_pw") String myNewPw);
}
