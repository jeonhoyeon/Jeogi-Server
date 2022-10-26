package com.howabout.there.popularcourse.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.howabout.there.findcourse.dto.testDto;

@Mapper
public interface IPopularCouresDao {
	
	//카테고리별 리스트 반환
	public ArrayList<testDto> setCourseCat(@Param("gender")String gender, @Param("age") String age, @Param("do") String location_do, @Param("si") String location_si);
	
	//DB기준 Do 반환
	public ArrayList<String> setDoList();
	
	//Do기준 Si 반환
	public ArrayList<String> setSiList(@Param("location_do") String location_do);
}
