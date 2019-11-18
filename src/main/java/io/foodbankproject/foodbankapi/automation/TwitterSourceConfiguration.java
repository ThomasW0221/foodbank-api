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
				.setOAuthConsumerKey(TwitterProperties.getConsumerKey())
				.setOAuthConsumerSecret(TwitterProperties.getConsumerSecret())
				.setOAuthAccessToken(TwitterProperties.getAccessToken())
				.setOAuthAccessTokenSecret(TwitterProperties.getAccessTokenSecret());
		return new TwitterFactory(configurationBuilder.build());
	}
}