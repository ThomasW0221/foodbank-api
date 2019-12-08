package io.foodbankproject.foodbankapi.statistics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import io.foodbankproject.foodbankapi.entity.Donation;
import io.foodbankproject.foodbankapi.service.FullDonationService;

public class DonationStatisticsCalculator implements StatisticsCalculator {
	
	List<Donation> donations;

	public DonationStatisticsCalculator(List<Donation> donations) {
		this.donations = donations;
	}

	public DonationStatisticCollection computeStatistics() {
		DonationStatisticCollection statCollection = new DonationStatisticCollection();
		
		
		return statCollection;
	}

}
