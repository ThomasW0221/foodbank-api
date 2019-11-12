package io.foodbankproject.foodbankapi.util;

import java.util.Map;

public class QueryConditionValidator {

	public static boolean donationIdPassedIn(Map<String, Object> queryParams) {
		return queryParams.get("donationId") != null;
	}
	
	public static boolean donorNameAndFromDatePassedIn(Map<String, Object> queryParams) {
		return queryParams.get("donorName") != null && queryParams.get("fromDate") != null;
	}
	
	public static boolean toDatePassedIn(Map<String, Object> queryParams) {
		return queryParams.get("toDate") != null;
	}
	
	public static boolean donorNamePassedIn(Map<String, Object> queryParams) {
		return queryParams.get("donorName") != null;
	}
	
	public static boolean fromDatePassedIn(Map<String, Object> queryParams) {
		return queryParams.get("fromDate") != null;
	}
	
	public static boolean minAndMaxWeightPassedIn(Map<String, Object> queryParams) {
		return queryParams.get("minWeight") != null && queryParams.get("maxWeight") != null;
	}
}
