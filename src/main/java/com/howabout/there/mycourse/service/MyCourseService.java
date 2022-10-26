package com.howabout.there.mycourse.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.howabout.there.mycourse.dao.IMyCourseDao;
import com.howabout.there.mycourse.dto.MyCourseDto;

@Service
public class MyCourseService implements IMyCourseService {

	@Autowired
	IMyCourseDao courseDao;
	
	public ArrayList<MyCourseDto> getMyCourse (ArrayList<JSONObject> myInfo)throws ParseException{
		String myId = myInfo.get(0).get("u_id").toString();
		ArrayList<MyCourseDto> myDataCourse = courseDao.getMyCourse(myId);
		return myDataCourse;
	}
	
	
		
	
	
}
