package org.jsp.service;

import org.jsp.dto.EmailConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class ResevationApiEmailService {

	@Autowired
	JavaMailSender mailSender;

	public String sendEmail(EmailConfiguration configuration) {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(configuration.getTo());
			helper.setText(configuration.getText());
			helper.setSubject(configuration.getSubject());
			mailSender.send(message);
			return "mail sent : ";
		} catch (MessagingException e) {
			e.printStackTrace();
			return "unable to send mail";
		}

	}
}
