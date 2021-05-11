package com.email.service;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import javax.mail.Transport;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;

@Service
public class emailService {

	// This is responsible to send Mail:
	public boolean sendMail(String message, String subject, String to) {
		// rest of the code
		boolean f = false;

		// Variable for gmail:
		String host = "smtp.gmail.com";
		String from = "shrutiamrutkar231@gmail.com";

		// Get the system Properties:
		Properties properties = System.getProperties();
		System.out.println("Properties=" + properties);

		// Setting important information to properties object:
		// set host

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Step-1:to get session object:
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("shrutiamrutkar231@gmail.com", "Shruti@123");
			}

		});

		session.setDebug(true);

		// Step-2:Compose the message [Text,multi media]
		MimeMessage m = new MimeMessage(session);

		try {
			// from email
			m.setFrom(from);

			// adding recipient to message:
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// adding subject to message
			m.setSubject(subject);

			// adding text to message
			m.setText(message);

			// send

			// Step-3:Send message using transport class
			Transport.send(m);

			System.out.println("Message sent Successfully!..");
			f=true;

		} catch (Exception e) {

			e.printStackTrace();

		}
		return f;

	}

}
