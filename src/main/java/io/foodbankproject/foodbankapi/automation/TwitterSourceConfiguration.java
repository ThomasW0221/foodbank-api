package io.foodbankproject.foodbankapi.automation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


@Configuration
public class TwitterSourceConfiguration {

	@Bean
	public TwitterFactory twitterFactory() {
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.setDebugEnabled(true)
				.setOAuthConsumerKey(System.getenv("TWITTER_CONSUMER_KEY"))
				.setOAuthConsumerSecret(System.getenv("TWITTER_CONSUMER_SECRET"))
				.setOAuthAccessToken(System.getenv("TWITTER_ACCESS_TOKEN"))
				.setOAuthAccessTokenSecret(System.getenv("TWITTER_ACCESS_SECRET"));
		return new TwitterFactory(configurationBuilder.build());
	}
} 
