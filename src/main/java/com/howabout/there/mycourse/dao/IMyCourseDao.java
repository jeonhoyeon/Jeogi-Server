package com.howabout.there.mycourse.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.howabout.there.mycourse.dto.MyCourseDto;

@Mapper
public interface IMyCourseDao {
	
	public ArrayList<MyCourseDto> getMyCourse(@Param("u_id") String myInfo);
}
