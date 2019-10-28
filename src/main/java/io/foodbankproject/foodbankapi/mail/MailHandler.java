package io.foodbankproject.foodbankapi.mail;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

public class MailHandler {

	public static void main(String[] args) throws Exception {
		sendMail("unccfoodpantry123@gmail.com", "Water bottles");
	}

	public static void sendMail(String recepient, String donation) throws Exception {

		System.out.println("Preparing to send email...");

		Properties properties = new Properties();

		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		String myEmail = "unccfoodpantry123@gmail.com", myPassword = "pantry123uncc9985";

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myEmail, myPassword);
			}
		});

		Message message = prepareMessage(session, myEmail, recepient, donation);

		Transport.send(message);
		System.out.println("Email sent");
	}

	private static Message prepareMessage(Session session, String myEmail, String recepient, String donation) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myEmail)); // set from email

			// set the recepient of the email
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));

			// handle setting subject, body text
			message.setSubject("Example email");
			
			/**
			 * Testing sending html content here - works
			 */
	/*		String html = "<h1> TESTING </h1> <br/> <h2> <b> Next test </b> </h2>";
			message.setContent(html, "text/html"); */
			
			message.setText("This is just an example email. \nDon't read too much " + "into it."
					+ "\n\nThank you for donating " + donation + "\n\nNicolas Tahan (Group 13)");

			return message;
		} catch (Exception e) {
		}
		return null;
	}

}
