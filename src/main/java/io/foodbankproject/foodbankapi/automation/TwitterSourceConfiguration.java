package io.foodbankproject.foodbankapi.automation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Twitter configuration taken in part from
 * https://github.com/snicoll/spring-boot-starter-twitter4j
 */
@EnableConfigurationProperties(TwitterProperties.class)
public class TwitterSourceConfiguration {

	public TwitterSourceConfiguration(TwitterProperties properties) {
	}

	@Bean
	@ConditionalOnMissingBean
	public TwitterFactory twitterFactory() {
		if (TwitterProperties.getConsumerKey() == null
				|| TwitterProperties.getConsumerSecret() == null
				|| TwitterProperties.getAccessToken() == null
				|| TwitterProperties.getAccessTokenSecret() == null) {
			throw new RuntimeException(
					"Twitter properties not configured properly. Please check twitter.* properties settings in configuration file.");
		}

		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.setDebugEnabled(false)
				.setOAuthConsumerKey(TwitterProperties.getConsumerKey())
				.setOAuthConsumerSecret(TwitterProperties.getConsumerSecret())
				.setOAuthAccessToken(TwitterProperties.getAccessToken())
				.setOAuthAccessTokenSecret(TwitterProperties.getAccessTokenSecret());
		return new TwitterFactory(configurationBuilder.build());
	}
}