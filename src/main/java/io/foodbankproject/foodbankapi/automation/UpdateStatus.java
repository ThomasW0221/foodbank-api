package io.foodbankproject.foodbankapi.automation;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;


public class UpdateStatus{
	
	public static void main(String[] args) {
		try {
			Twitter twitter = new TwitterFactory().getInstance();

			twitter.setOAuthConsumer(TwitterProperties.getConsumerKey(), TwitterProperties.getConsumerSecret());
			AccessToken accessToken = new AccessToken(TwitterProperties.getAccessToken(),
					TwitterProperties.getAccessTokenSecret());

			twitter.setOAuthAccessToken(accessToken);

			twitter.updateStatus("Testing part 4");

			System.out.println("Successfully updated the status in Twitter.");
		} catch (TwitterException te) {
			te.printStackTrace();
		}
	}	
}