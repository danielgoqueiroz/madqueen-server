package com.danielqueiroz.madqueenserver.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

	public void sendMessage(com.danielqueiroz.madqueenserver.model.Message messageForm) {
		
		String to = "contato@madqueenrock.com";
		String user = "site@madqueenrock.com";
		String password = "M@dqueen";
		String host = "mail.madqueenrock.com";

		Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(messageForm.getFrom()));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(messageForm.getSubject());
			message.setText(messageForm.getMessage());

			Transport.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}
