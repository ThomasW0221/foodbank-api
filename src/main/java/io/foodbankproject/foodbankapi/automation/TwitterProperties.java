package io.foodbankproject.foodbankapi.automation;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "twitter")
public class TwitterProperties {

	private static String consumerKey = "rwzaz7fPJtNnD4d73h5I9ug1Y";
	private static String consumerSecret = "Lsf9QE1ZZTLB6x88ruQjMlMVIEeRtr0bbkoy6nrq86HuNn2E7q";
	private static String accessToken = "1188888059397328896-RsAbb7DqtiSRZw9ubVdjE0duodvVQg";
	private static String accessTokenSecret = "rusqWhvVIkBo2p77OLTiroLfR8DTUTb3YYw8NdVC0ARub";

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
