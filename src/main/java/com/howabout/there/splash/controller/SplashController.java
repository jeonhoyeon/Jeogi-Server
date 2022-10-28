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
		System.out.println(newToken.get("jwt"));
		return newToken;
	}
}
