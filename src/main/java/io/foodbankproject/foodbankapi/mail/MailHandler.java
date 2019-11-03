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

		Message message = prepareMessage(session, myEmail, recipient, recipientName, donation);

		Transport.send(message);
		System.out.println("Email sent");
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
			message.setFrom(new InternetAddress(myEmail)); // set from email

			// set the recepient of the email
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

			// handle setting subject, body text
			message.setSubject("Example email");
			
			/**
			 * Testing sending html content here - works
			 */
	/*		String html = "<h1> TESTING </h1> <br/> <h2> <b> Next test </b> </h2>";
			message.setContent(html, "text/html"); */
			
			message.setText("Thank you " + recipientName + " for donating." + "\nWe have received"
					+ " your donation " + donation);

			return message;
		} catch (Exception e) {
		}
		return null;
	}

}
