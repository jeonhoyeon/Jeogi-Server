package com.howabout.there.popularcourse.service;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howabout.there.findcourse.dto.testDto;
import com.howabout.there.popularcourse.SetCourse;
import com.howabout.there.popularcourse.dao.IPopularCouresDao;

@Service
public class PopularCourseService implements IPopularCourseService{

	@Autowired
	IPopularCouresDao popularDao;
	
	public ArrayList<testDto> setCourseCat(ArrayList<JSONObject> setCourseInfo){
		SetCourse setInfo = new SetCourse();
		setInfo.setInfo(setCourseInfo);
		ArrayList<testDto> testReturn = popularDao.setCourseCat(setInfo.getGender(), setInfo.getAge(), setInfo.getLocation_do(), setInfo.getLocation_si());
		return testReturn;
	}
	
	public ArrayList<String> setDoList(){
		ArrayList<String> returnDoList = popularDao.setDoList();
		returnDoList.add(0, "전체");
		return returnDoList;
	}
	
	public ArrayList<String> setSiList(String choiceDo){
		String setSi; 
		System.out.println(choiceDo);
		if(choiceDo.equals("전체")) {
			setSi = "";
		}else {
			setSi = " r_do= "+choiceDo+" ";
		}
		ArrayList<String> returnDoList = popularDao.setSiList(setSi);
		returnDoList.add(0, "전체");
		return returnDoList;
	}
	
}
