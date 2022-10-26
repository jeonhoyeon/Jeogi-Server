package com.howabout.there.mypage.dao;

import org.apache.ibatis.annotations.Mapper;

import com.howabout.there.mypage.dto.UserDto;
import com.howabout.there.sign.vo.UserVo;

@Mapper
public interface IMyPageDao {
	
	//회원정보 수정. id값으로 수정 내용 update
	public void updateUser(UserVo uservo);
	
	//회원정보 select
	public UserDto selectUser(String id);
	
	//비밀번호 확인. 비밀번호 일치하면 1, 불일치하면 0
	public int pwCheck(String u_id, String u_pw);
	
	// 회원탈퇴 flag를 0으로 바꿈
	public void withdrawal(String id);
	//회원탈퇴 사유를 저장
	public void reason(String why);

}
