package com.howabout.there.findcourse.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.howabout.there.findcourse.cafeDto;
import com.howabout.there.findcourse.myCourseDto;
import com.howabout.there.findcourse.restDto;


@Mapper
public interface IFindCourseDao {
	// 유저 코스 저장 & 저장한 코스 id 반환
	public boolean courseSave(myCourseDto courseDto);
	public boolean courseDibs(@Param("u_id")String u_id,@Param("r_id")String r_id,@Param("c_id")String c_id);
	
	// REST 저장
	public void restSave(restDto restDto);
	
	// CAFE 저장
	public void cafeSave(cafeDto cafeDto);
	
	//rest, cafe 존재 여부 확인
	public int restCheck(restDto restDto);
	public int cafeCheck(cafeDto cafetDto);
	
	public Map getReview(@Param("url")String url);
	
	public void inputCrawler(@Param("url")String url,@Param("imgUrl")String imgUrl,
			 @Param("storeTime")String storeTime, @Param("star")String star,@Param("review1")String review1,
			 @Param("review2")String review2, @Param("review3")String review3,@Param("insertTime")Timestamp time);	
	
}
