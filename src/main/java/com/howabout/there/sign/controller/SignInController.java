package com.howabout.there.sign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.howabout.there.sign.service.mailTestService;



@Controller
public class SignInController {
	
	@Autowired
	mailTestService mail;
	
	
	@RequestMapping("/login/signIn")	
	public String loginPage() {
		mail.send();
		return "login";
	}
}
