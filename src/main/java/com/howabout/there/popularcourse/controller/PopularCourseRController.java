 package com.howabout.there.popularcourse.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.howabout.there.findcourse.dto.testDto;
import com.howabout.there.popularcourse.service.PopularCourseService;



@RestController
public class PopularCourseRController {
	
	@Autowired
	PopularCourseService popularService;
	
	//카테고리 별로 리스트 반환
	@PostMapping("/popularCourse/getCatCourse")
	public ArrayList<testDto> getCatCourse(@RequestBody ArrayList<JSONObject> setCatData){
		ArrayList<testDto> returnquery = popularService.setCourseCat(setCatData);
		return returnquery;
	}
	
	//DB내부 Do 반환
	@GetMapping("/popularCourse/getDo")
	public ArrayList<String> getDo(){
		ArrayList<String> returnDo = popularService.setDoList();
		return returnDo;
	}
	
	//DB내부 Do를 기준으로 Si 반환
	@PostMapping("/popularCourse/getSi")
	public ArrayList<String> getSi(@RequestBody String choiceDo){
		ArrayList<String> returnDo = popularService.setSiList(choiceDo);
		return returnDo;
	}
	
	//TEST CAT 쿼리문
	@GetMapping("/testQuery")
	public ArrayList<testDto> testQuery(){
		//TEST 데이터 셋팅!
		JSONObject setTestJSON = new JSONObject();
		setTestJSON.put("gender", "전체");
		setTestJSON.put("age", "전체");
		setTestJSON.put("location_do", "전체");
		setTestJSON.put("location_si", "전체");
		ArrayList<JSONObject> setTestArray = new ArrayList<>();
		setTestArray.add(setTestJSON);
		
		ArrayList<testDto> returntestquery = popularService.setCourseCat(setTestArray);
		System.out.println("TEST 5 : "+returntestquery);
		return returntestquery;
	}
	

}
