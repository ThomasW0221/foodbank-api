package io.foodbankproject.foodbankapi.mail;

public class MailHandlerExecutor implements Runnable {

	private MailHandler mailHandler;
	
	public MailHandlerExecutor (MailHandler mailHandler) {
		this.mailHandler = mailHandler;
	}
	
	@Override
	public void run() {
		
		try {
			mailHandler.sendMail();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
