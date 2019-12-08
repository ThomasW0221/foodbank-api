package io.foodbankproject.foodbankapi.statistics;

import java.util.List;

import io.foodbankproject.foodbankapi.entity.Donation;

public class DonationStatisticsCalculator implements StatisticsCalculator {
	
	List<Donation> donations;

	public DonationStatisticsCalculator(List<Donation> donations) {
		this.donations = donations;
	}

	public DonationStatisticCollection computeStatistics() {
		DonationStatisticCollection statCollection = new DonationStatisticCollection();
		for(Donation donation : donations) {
			
		}
		
		return statCollection;
	}

}
