package io.foodbankproject.foodbankapi.automation;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Component
public class UpdateStatus{	
	
	TwitterFactory ourTwitterFactory;
	Twitter twitterInstance;
	LocalDateTime ldt = LocalDateTime.now();
	
	@Autowired
	public UpdateStatus(TwitterFactory twitterFactory) {
		ourTwitterFactory = twitterFactory;
		twitterInstance = twitterFactory.getInstance();
	}
	
	@Scheduled(fixedRate=5000)
	public void testTweet() {
		try {
			twitterInstance.updateStatus(ldt.toString());
			ldt = ldt.plusDays(1);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
}