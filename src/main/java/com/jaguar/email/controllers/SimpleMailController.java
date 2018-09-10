package com.jaguar.email.controllers;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaguar.email.services.AsyncMailService;

@RestController
public class SimpleMailController {
	
	@Autowired
	private JavaMailSender sender;

	@RequestMapping("/sendMail")
	public String sendMail() {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			//helper.setTo("vats.bhumi@gmail.com");
			helper.setTo("developer.ankit.verma5620@gmail.com");
			helper.setText("Hi Bhumika,\r\n" + 
					"\r\n" +
					"Through each day our goal is\r\n" + 
					"to touch one’s heart;\r\n" + 
					"encourage one’s mind nd\r\n" + 
					"inspire one’s soul.\r\n" + 
					"May u continually b blessed nd\r\n" + 
					"be a blessing to others! :)\r\n\r\n" +
					"Regards,\r\n" +
					"Ankit :) ,\r\n");
			helper.setSubject("Mail From Ankit application");
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Error while sending mail ..";
		}
		sender.send(message);
		return "Mail Sent Success!";
	}

	@RequestMapping("/sendMailAtt")
	public String sendMailAttachment() throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		try {
			helper.setTo("developer.ankit.verma5620@gmail.com");
			helper.setText("Greetings :)\n Please find the attached docuemnt for your reference.");
			helper.setSubject("Mail From Spring Boot");
			ClassPathResource file = new ClassPathResource("document.PNG");
			helper.addAttachment("document.PNG", file);
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Error while sending mail ..";
		}
		sender.send(message);
		return "Mail Sent Success!";
	}
	
}
