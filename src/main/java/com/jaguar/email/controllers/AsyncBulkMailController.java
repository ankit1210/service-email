package com.jaguar.email.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jaguar.email.services.AsyncMailService;

public class AsyncBulkMailController {
	
	@Autowired
	private JavaMailSender sender;
	
	@RequestMapping("/sendAsync")
	public String sendAsyncMail() {
		AsyncMailService asyncMailService = new AsyncMailService(sender);
		Thread t = new Thread(asyncMailService);
		t.start();
		return "Mail Sent Success!";
	}

}
