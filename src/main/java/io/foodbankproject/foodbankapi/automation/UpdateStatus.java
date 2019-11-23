package io.foodbankproject.foodbankapi.automation;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.foodbankproject.foodbankapi.entity.Donation;
import io.foodbankproject.foodbankapi.service.FullDonationService;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Component
public class UpdateStatus implements SocialMediaPoster {	
	
	TwitterFactory ourTwitterFactory;
	Twitter twitterInstance;
	FullDonationService fullDonationService;
	
	@Autowired
	public UpdateStatus(TwitterFactory twitterFactory, FullDonationService fullDonationService) {
		this.ourTwitterFactory = twitterFactory;
		this.twitterInstance = twitterFactory.getInstance();
		this.fullDonationService = fullDonationService;
	}
	
	@Override
	@Scheduled(cron="0 18 * * 1-5")
	public void makePost() {
		LocalDateTime today = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
		String todayString = today.toString();
		List<Donation> todaysDonations = getTodaysDonations(todayString);
		int numberOfTodaysDonations = todaysDonations.size();
		
		String tweetText = String.format("On %s, we received %d donations. Stop by and donate today!",
				todayString,
				numberOfTodaysDonations);
		
		try {
			twitterInstance.updateStatus(tweetText);
		} catch (TwitterException twe) {
			twe.printStackTrace();
		}
	}

	private List<Donation> getTodaysDonations(String todaysDate) {
		return fullDonationService.donationFindByFromDate(todaysDate);
	}

	
}