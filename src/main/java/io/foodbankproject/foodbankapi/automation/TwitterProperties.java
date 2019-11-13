package io.foodbankproject.foodbankapi.automation;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "twitter")
public class TwitterProperties {

	/**keys go in the ""*/
	private static String consumerKey = "";
	private static String consumerSecret = "";
	private static String accessToken = "";
	private static String accessTokenSecret = "";

	public static String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		TwitterProperties.consumerKey = consumerKey;
	}

	public static String getConsumerSecret() {
		return consumerSecret;
	}

	public void setConsumerSecret(String consumerSecret) {
		TwitterProperties.consumerSecret = consumerSecret;
	}

	public static String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		TwitterProperties.accessToken = accessToken;
	}

	public static String getAccessTokenSecret() {
		return accessTokenSecret;
	}

	public void setAccessTokenSecret(String accessTokenSecret) {
		TwitterProperties.accessTokenSecret = accessTokenSecret;
	}
}
