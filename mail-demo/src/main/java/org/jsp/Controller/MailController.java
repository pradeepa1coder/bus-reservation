package org.jsp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@RestController
public class MailController {

	@Autowired
	JavaMailSender mailSender;

	@PostMapping("/email")
	public String sendMail(@RequestParam String email) {
//SimpleMailMessage-MVC
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(email);
			helper.setSubject("Checking email");
			helper.setText("Hi pradeep" + "How are you" + "Im missing you so much");

			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return "Yes email has sent to : " + email;
	}

}