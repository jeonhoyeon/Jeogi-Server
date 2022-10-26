package com.howabout.there.findcourse.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.howabout.there.findcourse.JsoupReview;
import com.howabout.there.findcourse.service.FindCourseService;





@RestController
public class FindCourseRController {

   
   @Autowired
   FindCourseService courseService;

   // 식당 리스트 업
   @PostMapping("/findCourse/rest")
   public ArrayList<JSONObject> rest_list(@RequestBody ArrayList<JSONObject> inputData) throws ParseException {
      String category = "FD6"; // 음식점 카테고리
      ArrayList<JSONObject> result = courseService.listUp(inputData, category);
      return result;
   }
   
   // 카페 시스트 업
   @PostMapping("/findCourse/cafe")
   public ArrayList<JSONObject> cafe_list(@RequestBody ArrayList<JSONObject> inputData) throws ParseException {
      System.out.println(inputData+"");
      String category = "CE7"; // 카페 카테고리
      ArrayList<JSONObject> result = courseService.listUp(inputData, category);
      return result;
      }
   
   //완성된 코스를 restaurant, cafe, course TABLE에 저장
   @PostMapping("/findCourse/saveCourse")
   public Map saveCourse(@RequestBody ArrayList<JSONObject> coursedata) throws Exception {
      System.out.println("SAVE COURSE :  "+coursedata.toString());
   
      Map answer = courseService.saveCourse(coursedata);
      return answer;
   }
   
   // 내코스 찜하기 
   @PostMapping("/findCourse/courseDibs")
   public int courseDibs(@RequestBody Map dibsData) {
	   System.out.println("DIBSs.. : "+ dibsData.toString());
      int SuccessDibs = courseService.courseDibs(dibsData);
      return SuccessDibs;
   }
   

   //장소정보 이미지 경로 + 사용자 리뷰를 불러와 줌
   @PostMapping("/findCourse/getLocationInfo")
   public Map getLocationInfo(@RequestBody Map locationInfo) throws Exception {
      Map returnList = courseService.getLocationList(locationInfo);
      return returnList;
   }
   
   
   @GetMapping("/testCr")
   public String asdsadadsdsadsd() throws ParseException, Exception {
      JsoupReview rrr = new JsoupReview();
      String aaddss = "http://place.map.kakao.com/10487761";
      Map radidas = new HashMap<>();
      radidas.put("place_url", "http://place.map.kakao.com/10050832");
      
      Map returnList = courseService.getLocationList(radidas);
      
      return returnList.toString();
   }
   

      
}