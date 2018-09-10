package com.jaguar.email.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class AsyncMailService implements Runnable {

    private static final int MAX_RETRY_COUNT = 3;

    private static final Logger logger = LoggerFactory.getLogger(AsyncMailService.class);
    
    //@Autowired
	private JavaMailSender sender;

    public AsyncMailService(JavaMailSender javaMailSender) {
    	sender = javaMailSender;
    }

	@Override
    public void run() {
    	
    	MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo("developer.ankit.verma5620@gmail.com");
			helper.setText("Hi, Its a test message");
			helper.setSubject("Mail From Ankit application");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
    	sender.send(message);
    }

}
