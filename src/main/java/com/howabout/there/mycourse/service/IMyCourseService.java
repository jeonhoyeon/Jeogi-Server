package com.howabout.there.mycourse.service;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestBody;

import com.howabout.there.mycourse.dto.MyCourseDto;

public interface IMyCourseService {
	
	
	public ArrayList<MyCourseDto> getMyCourse (ArrayList<JSONObject> myInfo)throws ParseException;;
	
}
