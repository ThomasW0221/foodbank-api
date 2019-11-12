package io.foodbankproject.foodbankapi.util;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import io.foodbankproject.foodbankapi.service.FullDonationService;

public class DonationUtility {

	private Map<String, Object> queryParams;
	
	@Autowired
	private FullDonationService fullDonationService;
	
	
	public DonationUtility(Map<String, Object> queryParams) {
		this.queryParams = queryParams;
	}

	public ResponseEntity<?> performDonationGet() {
		if (QueryConditionValidator.donationIdPassedIn(queryParams)) {
			return performDonationQueryByIdOrReturnNotFound();
		} else if (QueryConditionValidator.donorNameAndFromDatePassedIn(queryParams)) {
			return performDonationQueryByNameAndDate();
		} else if (QueryConditionValidator.donorNamePassedIn(queryParams)) {
			return performDonationQueryByDonorName();
		} else if (QueryConditionValidator.fromDatePassedIn(queryParams)) {
			return performDonationQueryByDates();
		} else if (QueryConditionValidator.minAndMaxWeightPassedIn(queryParams)) {
			return performDonationQueryByWeight();
		} else {
			return performDefaultQuery();
		}
	}

	private ResponseEntity<?> performDefaultQuery() {
		LocalDateTime time = LocalDateTime.now();
		time = time.minusDays(30);
		String timeString = String.format("%04d-%02d-%02d", time.getYear(), time.getMonthValue(), time.getDayOfMonth());
		return ResponseEntity.ok(fullDonationService.donationFindByFromDate(timeString));
	}

	private ResponseEntity<?> performDonationQueryByWeight() {
		Integer minWeight = (Integer) queryParams.get("minWeight");
		Integer maxWeight = (Integer) queryParams.get("maxWeight");
		return ResponseEntity.ok(fullDonationService.donationFindByWeightRange(minWeight, maxWeight));
	}

	private ResponseEntity<?> performDonationQueryByDates() {
		String fromDate = (String) queryParams.get("fromDate");
		
		if (QueryConditionValidator.toDatePassedIn(queryParams)) {
			String toDate = (String) queryParams.get("toDate");
			return ResponseEntity.ok(fullDonationService.donationFindByFromAndToDate(fromDate, toDate));
		}
		
		return ResponseEntity.ok(fullDonationService.donationFindByFromDate(fromDate));
	}

	private ResponseEntity<?> performDonationQueryByDonorName() {
		String donorName = (String) queryParams.get("donorName");
		return ResponseEntity.ok(fullDonationService.donationFindByName(donorName));
	}

	private ResponseEntity<?> performDonationQueryByIdOrReturnNotFound() {
		Integer donationId = (Integer)queryParams.get("donationId");
		if (fullDonationService.donationExistsById(donationId)) {
			return ResponseEntity.ok(fullDonationService.donationFindById(donationId));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	private ResponseEntity<?> performDonationQueryByNameAndDate() {
		String donorName = (String) queryParams.get("donorName");
		String fromDate = (String) queryParams.get("fromDate");
		
		if (QueryConditionValidator.toDatePassedIn(queryParams)) {
			String toDate = (String) queryParams.get("toDate");
			return ResponseEntity.ok(fullDonationService.donationFindByNameFromDateAndToDate(donorName, fromDate, toDate));
		}
		
		return ResponseEntity.ok(fullDonationService.donationFindByNameAndFromDate(donorName, fromDate));
	}
}
