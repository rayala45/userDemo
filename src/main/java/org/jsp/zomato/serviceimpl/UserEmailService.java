package org.jsp.zomato.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserEmailService {
	@Autowired
	JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String user;
	
	public void sendEmail(String reciepient,String body,String subject) {
		SimpleMailMessage smm=new SimpleMailMessage();
		smm.setFrom(user);
		smm.setTo(reciepient);
		smm.setText(body);
		smm.setSubject(subject);
		javaMailSender.send(smm);
	}

}
