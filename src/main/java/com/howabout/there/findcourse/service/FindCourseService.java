package com.howabout.there.findcourse.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.howabout.there.findcourse.JsoupReview;
import com.howabout.there.findcourse.cafeDto;
import com.howabout.there.findcourse.kakaoApi;
import com.howabout.there.findcourse.myCourseDto;
import com.howabout.there.findcourse.restDto;
import com.howabout.there.findcourse.dao.IFindCourseDao;
import com.howabout.there.mypage.dto.UserDto;
import com.howabout.there.mypage.service.MyPageService;

@Service
public class FindCourseService implements IFindCourseService{

   @Autowired
   IFindCourseDao courseDao;
   
   @Autowired
   MyPageService myPageService;
   
   // 장소 정보 크롤링 or DB에서 꺼내기
   public Map getLocationList(Map locationName) throws ParseException, Exception{
      
      String place_url = locationName.get("place_url").toString();
      
      Map returnList  = new HashMap<>();
      try {
    	  System.out.println("TTTTTTTTTTTTTTCCCCCCCCCCCCCC");
         returnList = courseDao.getReview(place_url);
      }catch(Exception e){
    	  System.out.println("22222222222222222222222222222222222222"+ returnList);
      }
//         returnList.get(2);
         if(returnList==null) {
         System.out.println("TEST TRY");
//      }catch(Exception e){
         System.out.println("TEST TTT");
         JsoupReview jsoupCrawler = new JsoupReview();
         returnList= jsoupCrawler.getReview(place_url, 1);
         System.out.println("333333333333333333333333"+ returnList);
         courseDao.inputCrawler(place_url,(String)returnList.get("imgUrl"),(String)returnList.get("storeTime"),(String)returnList.get("starpoint"),(String)returnList.get("review_1"),(String)returnList.get("review_2"),(String)returnList.get("review_3"),Timestamp.valueOf(LocalDateTime.now()));
         
      }
      
      
      
      return returnList;
   }
   
   // 내코스 짐하기
   public int courseDibs(Map dibsData) {
      boolean booleanDibs = courseDao.courseDibs((String)dibsData.get("u_id"),(String)dibsData.get("r_id"),(String)dibsData.get("c_id"));
      int successDibs;
      if(booleanDibs = true) {
         successDibs=1;
      }else {
         successDibs=0;
      }
      return successDibs;
   }
   
   // 카카오API사용 카페, 식당 리스트업   
   public ArrayList<JSONObject> listUp(ArrayList<JSONObject> inputData, String category) throws ParseException{
      kakaoApi kakao = new kakaoApi();
      ArrayList<JSONObject> returnData = kakao.listUp(inputData, category);
      return returnData;
   }
   
   // 연결 코스 저장 
   public Map saveCourse(@RequestBody ArrayList<JSONObject> data) throws ParseException, Exception{
      System.out.println(data.toString());
      myCourseDto myDto = new myCourseDto();
      restDto rest = new restDto();
      cafeDto cafe = new cafeDto();
      System.out.println("TEST1111");
      JsoupReview getImgCrawler = new JsoupReview();
      System.out.println("TEST22222");
      String r_image_url = "";
      String c_image_url = "";
      Map r_review = new HashMap<>();
      Map c_review = new HashMap();
      
      
      //TEST REST REVIEW
//      try {
         System.out.println("REST URL : "+data.get(1).get("place_url").toString());
         r_review = courseDao.getReview(data.get(1).get("place_url").toString());
//         String aa = r_review.get(2);
         if(r_review==null) {
         System.out.println("TEST TRY");
//      }catch(Exception e){
         System.out.println("TEST TRY");
         r_review= getImgCrawler.getReview(data.get(1).get("place_url").toString(), 1); 
         courseDao.inputCrawler(data.get(1).get("place_url").toString(),(String)r_review.get("imgUrl"),(String)r_review.get("storeTime"),
        		 (String)r_review.get("starpoint"),(String)r_review.get("review_1"),
        		 (String)r_review.get("review_2"),(String)r_review.get("review_3"),
        		 Timestamp.valueOf(LocalDateTime.now()));
         
         
      }
      //TEST CAFE REVIEW
//      try {
         System.out.println("CAFE URL : "+data.get(2).get("place_url").toString());
         c_review = courseDao.getReview(data.get(2).get("place_url").toString());
//         String bb = c_review.get(2);
         if(c_review==null) {
         System.out.println("TEST TRY");
//      }catch(Exception g){
         System.out.println("TEST TRY");
         c_review= getImgCrawler.getReview(data.get(2).get("place_url").toString(), 1);   
         courseDao.inputCrawler(data.get(2).get("place_url").toString(),(String)c_review.get("imgUrl"),(String)c_review.get("storeTime"),
        		 (String)c_review.get("starpoint"),(String)c_review.get("review_1"),
        		 (String)c_review.get("review_2"),(String)c_review.get("review_3"),
        		 Timestamp.valueOf(LocalDateTime.now()));
         
      }

      
//      
//      try {
//         System.out.println("TEST333333");
//         r_review = getImgCrawler.getReview(data.get(1).get("place_url").toString(), 1);
//         System.out.println(r_image_url);
//         c_review = getImgCrawler.getReview(data.get(2).get("place_url").toString(), 1);
//         System.out.println(c_image_url);
//      } catch (InterruptedException e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      }
      //여기서 리뷰 업데이트까지 할 것인가 .. 말 것인가 .
      
      Map returnMap = new HashMap<>();
      
      UserDto myInfo = myPageService.userListUp(data);

      
      //내정보 세팅
      myDto.setWriter(myInfo.getU_nick());
      myDto.setRest_name(data.get(1).get("place_name").toString());
      myDto.setRest_id(data.get(1).get("id").toString());
      myDto.setCafe_name(data.get(2).get("place_name").toString());
      myDto.setCafe_id(data.get(2).get("id").toString());
      myDto.setC_insert_time(Timestamp.valueOf(LocalDateTime.now()));
      myDto.setC_update_time(Timestamp.valueOf(LocalDateTime.now()));
      myDto.setGender(myInfo.getGender());
      myDto.setBirth(myInfo.getBirth());
      myDto.setFlag(0);
      
      //식당정보 세팅   경도=x=lon, 위도=y=lat
      String[] restlocation = data.get(1).get("address_name").toString().split(" ");
      String[] restCat = data.get(1).get("category_name").toString().split(" ");
      rest.setR_name(data.get(1).get("place_name").toString());
      rest.setR_phone(data.get(1).get("phone").toString());
      rest.setR_lon(data.get(1).get("x").toString());
      rest.setR_lat(data.get(1).get("y").toString());
      rest.setR_do(restlocation[0]);
      rest.setR_si(restlocation[1]);
      rest.setR_gu(restlocation[2]);
      rest.setR_dong(restlocation[3]);
      rest.setR_cat(restCat[restCat.length-1]);
      rest.setR_id(data.get(1).get("id").toString());
      rest.setR_url(data.get(1).get("place_url").toString());
      rest.setR_insert_time(Timestamp.valueOf(LocalDateTime.now()));
      rest.setR_update_time(Timestamp.valueOf(LocalDateTime.now()));
      rest.setR_image_url(r_review.get("imgUrl").toString());
      
      //카페정보 세팅
      String[] cafeLocation = data.get(2).get("address_name").toString().split(" ");
      String[] cafeCat = data.get(2).get("category_name").toString().split(" ");
      cafe.setC_name(data.get(2).get("place_name").toString());
      cafe.setC_phone(data.get(2).get("phone").toString());
      cafe.setC_lon(data.get(2).get("x").toString());
      cafe.setC_lat(data.get(2).get("y").toString());
      cafe.setC_do(cafeLocation[0]);
      cafe.setC_si(cafeLocation[1]);
      cafe.setC_gu(cafeLocation[2]);
      cafe.setC_dong(cafeLocation[3]);
      cafe.setC_cat(cafeCat[cafeCat.length-1]);
      cafe.setC_id(data.get(2).get("id").toString());
      cafe.setC_url(data.get(2).get("place_url").toString());
      cafe.setC_insert_time(Timestamp.valueOf(LocalDateTime.now()));
      cafe.setC_update_time(Timestamp.valueOf(LocalDateTime.now()));
      cafe.setC_image_url(c_review.get("imgUrl").toString());   
      
      int restCheck = courseDao.restCheck(rest);
      int cafeCheck = courseDao.cafeCheck(cafe);
      
      if(restCheck==0)
         courseDao.restSave(rest);
      
      if(cafeCheck==0)
         courseDao.cafeSave(cafe);
      
      boolean resultSave = courseDao.courseSave(myDto);
      
      if(resultSave=true) {
         returnMap.put("r_id", data.get(1).get("id").toString()) ;
         returnMap.put("c_id", data.get(2).get("id").toString()) ;      
      }else {
         returnMap.put("r_id", "업데이트 실패");
      }
      return returnMap;
      
   }

}
   
   
