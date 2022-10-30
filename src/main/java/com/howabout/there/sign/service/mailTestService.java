package com.howabout.there.sign.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class mailTestService {

	@Autowired
	JavaMailSender mailSender;
	
	public void send() {
		System.out.println("mail service start q");
		String testmail ="2091106@naver.com";
		SimpleMailMessage simple = new SimpleMailMessage();
		simple.setTo(testmail);
		
		simple.setSubject("Test MAIL");
		simple.setText("SEND TEST OK");
		mailSender.send(simple);
	}
}
