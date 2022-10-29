package com.howabout.there.splash.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.howabout.there.splash.service.SplashService;

@RestController
public class SplashController {

	@Autowired
	SplashService splash;
	
	@PostMapping("/splash/autoLogin")
	public Map refillToken(HttpServletRequest request) {
		System.out.println("SPLASH CONTROLLER");
		String token = request.getHeader("Authorization").substring(7);
		System.out.println(token);
		Map newToken  = splash.getToken(token);
		System.out.println("NEW TOKEN : "+ newToken.get("jwt"));
		return newToken;
	}
	
	@PostMapping("/splash/test")
	public String refi11123llToken(HttpServletRequest request) {
		System.out.println("NO TOKEN TEST");
		String token = null;
		try {
			token = request.getHeader("Authorization").substring(7);	
		}catch(NullPointerException e) {
			
		}
		if(token == null) {
			System.out.println("token is null");
		}else { System.out.println("token is not null");
		}
		return "CCULL";
	}
	
}
