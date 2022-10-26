package com.howabout.there.mycourse.controller;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.howabout.there.mycourse.dto.MyCourseDto;
import com.howabout.there.mycourse.service.MyCourseService;

@RestController
public class MyCourseRController {
	
	@Autowired
	MyCourseService myCourseService;
	
	// 내코스 리스트 업
	@PostMapping("/myCourse/myCourse")
		public ArrayList<MyCourseDto> getMyData(@RequestBody ArrayList<JSONObject> inputData) throws ParseException {
			ArrayList<MyCourseDto> result = myCourseService.getMyCourse(inputData);
			return result;
		}
	
}


