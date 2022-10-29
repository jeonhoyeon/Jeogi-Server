package com.howabout.there.mycourse.dao;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.howabout.there.findcourse.cafeDto;
import com.howabout.there.findcourse.restDto;
import com.howabout.there.mycourse.dto.InputCourseDto;
import com.howabout.there.mycourse.dto.MyCourseDto;

@Mapper
public interface IMyCourseDao {
	
	public ArrayList<MyCourseDto> getMyCourse(@Param("u_nick") String myInfo);
	
	//rest, cafe 존재 여부 확인
	public int restCheck(restDto restDto);
	public int cafeCheck(cafeDto cafetDto);	
	// 존재하지 않으면 REST, CAFE 저장
	public void restSave(restDto restDto);
	public void cafeSave(cafeDto cafeDto);
		
	//userCourse Flag 값 가져오기
	public int checkFlag(@Param("u_nick")String u_id,@Param("r_id")String r_id,@Param("c_id")String c_id);
	
	// 유저 코스 저장 & 저장한 코스 id 반환
	public boolean courseSave(InputCourseDto courseDto);
	public boolean courseDibs(@Param("u_nick")String u_id,@Param("r_id")String r_id,@Param("c_id")String c_id, @Param("flag")int flagValue, @Param("time") Timestamp time  );
	
	
	// 사용자가 선택한 Course 가 존재하는지 확인 
	public int exisCheck(@Param("u_nick")String u_id,@Param("r_id")String r_id,@Param("c_id")String c_id);
	
	//id pw 가 일치하는 코스 한개 가지고 오기
	public InputCourseDto findOneCourse(@Param("r_id")String r_id,@Param("c_id")String c_id);
	
	

	
	
	
}
