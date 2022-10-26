package com.howabout.there.sign.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignInController {
	@RequestMapping("/login/signIn")	
	public String loginPage() {
		return "login";
	}
}
