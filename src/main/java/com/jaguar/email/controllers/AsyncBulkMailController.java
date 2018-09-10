package com.jaguar.email.controllers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaguar.email.services.AsyncMailService;

@RestController
public class AsyncBulkMailController {
	
	@Autowired
	private JavaMailSender sender;
	
	private static final int MYTHREADS = 30;
	
	@RequestMapping("/sendAsync")
	public String sendAsyncMail() {
		ExecutorService executor = Executors.newFixedThreadPool(MYTHREADS);
		AsyncMailService asyncMailService = new AsyncMailService(sender);
		executor.execute(asyncMailService);
		return "Mail Sent Success!";
	}

}
