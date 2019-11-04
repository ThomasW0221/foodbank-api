package io.foodbankproject.foodbankapi.automationutils;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class MailHandler {
	
	private Properties properties;
	
	private String username = "unccfoodpantry123@gmail.com";
	
	private String password = "pantry123uncc9985";
	
	
	public MailHandler() {
		addMailConfigProperties();
	}
	
	private void addMailConfigProperties() {
		properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
	}

	/**
	 * Method for sending mail, currently sends a message to a recipient and allows you to 
	 * declare what is being donated. 
	 * @param recipient
	 * @param recipientName
	 * @param donation
	 * @throws Exception
	 */
	public void sendMail(String recipient, String recipientName, String donation) throws Exception {

		System.out.println("Preparing to send email...");

		Session session = generateAuthenticationSession();
		System.out.println("session generated. preparing message");

		Message message = prepareMessage(session, username, recipient, recipientName, donation);
		System.out.println("message prepared. sending message");

		Transport.send(message);
		System.out.println("Email sent");
	}
	
	private Session generateAuthenticationSession() {
		System.out.println(username);
		System.out.println(password);
		return Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
	}

	/**
	 * Prepare the message to be sent to a recipient. 
	 * @param session - Pass in the session.
	 * @param myEmail - The email you're sending from.
	 * @param recepient - The recipient's email address.
	 * @param donation - What donation? Subject to change
	 * @return - A message object if successfull otherwise null.
	 */
	private static Message prepareMessage(Session session, String myEmail, 
			String recipient, String recipientName, String donation) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myEmail));

			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

			message.setSubject("Example email");
			
			message.setText("Thank you " + recipientName + " for donating." + "\nWe have received"
					+ " your donation " + donation);

			return message;
		} catch (Exception e) {
			System.out.println(e);
		} 
		return null;
	}

}
