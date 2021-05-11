package com.mail;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		System.out.println("Pepaaring to Send message");

		// Mail Structure
		String message = "Hello !! I am Shruti Amrutkar... ";
		String sub = "Mail sending project Exaample";
		String to = "shrutiamrutkar177@gmail.com";
		String from = "shrutiamrutkar231@gmail.com";

		// Static Method/Function
		sendMail(message, sub, to, from);
		sendAttatch(message, sub, to, from);

	}

	// This is responsible to send Mail:
	private static void sendMail(String message, String sub, String to, String from) {

		// Variable for gmail:
		String host = "smtp.gmail.com";

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
			m.setSubject(sub);

			// adding text to message
			m.setText(message);

			// send

			// Step-3:Send message using transport class
			Transport.send(m);

			System.out.println("Message sent Successfully!..");

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	
	//This is responsible to send message with attatchment:
	private static void sendAttatch(String message, String sub, String to, String from) {

		// Variable for gmail:
		String host = "smtp.gmail.com";

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
			m.setSubject(sub);
			
			
			
			
			//attatchment
			//file path:
			
			String path="C:\\Users\\Shruti\\Pictures\\2021\\SHRUTI (2).jpg";
			
			MimeMultipart mimeMultipart = new MimeMultipart();
			//text
			//file
			
		
			
			//text
			MimeBodyPart textMime = new MimeBodyPart();
			
			//file
		    MimeBodyPart fileMime = new MimeBodyPart();
		    
		    try
		    {
		    	textMime.setText(message);
		    	File file=new File(path);
		    	fileMime.attachFile(file);
		    	
		    	mimeMultipart.addBodyPart(textMime);
		    	mimeMultipart.addBodyPart(fileMime);
		    	
		    }
		    catch (Exception e)
		    {
				e.printStackTrace();
			}
		    
		    
			m.setContent(mimeMultipart);
			

	
			// send

			// Step-3:Send message using transport class
			Transport.send(m);
			

			System.out.println("Message sent Successfully!..");

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
}
