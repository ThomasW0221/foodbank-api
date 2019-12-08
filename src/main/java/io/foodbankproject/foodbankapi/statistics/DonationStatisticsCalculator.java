package io.foodbankproject.foodbankapi.statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.foodbankproject.foodbankapi.entity.Donation;
import io.foodbankproject.foodbankapi.entity.Item;

public class DonationStatisticsCalculator implements StatisticsCalculator {
	
	List<Donation> donations;

	public DonationStatisticsCalculator(List<Donation> donations) {
		this.donations = donations;
	}

	public DonationStatisticCollection computeStatistics() {
		DonationStatisticCollection statCollection = new DonationStatisticCollection();
		statCollection.setMinWeight(99999);
		
		List<String> names = new ArrayList<>();
		List<String> itemNames = new ArrayList<>();
		
		int numberOfItemsDonated = 0;
		
		for(Donation donation : donations) {
			setMinOrMax(statCollection, donation);
			
			names.add(donation.getDonorName());
			for(Item item : donation.getItemsDonated()) {
				itemNames.add(item.getName());
			}
			
			statCollection.setTotalWeight(statCollection.getTotalWeight() + donation.getDonationWeight());
			numberOfItemsDonated += donation.getItemsDonated().size();
		}
		
		statCollection.setAvgWeight(statCollection.getTotalWeight() / donations.size());
		statCollection.setNumberOfItemsDonated(numberOfItemsDonated);
		
		if(names.size() > 0) {
			String mostFrequentDonor = calculateMostFrequentDonor(names);
			statCollection.setMostFrequentDonor(mostFrequentDonor);
		}
		
		if (itemNames.size() > 0) {
			String mostDonatedItem = calculateMostDonatedItem(itemNames);
			statCollection.setMostDonatedItem(mostDonatedItem);
		}
		
		return statCollection;
	}
	
	private String calculateMostFrequentDonor(List<String> names) {
		String mostFrequentDonor = names.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet()
                .stream().max(java.util.Comparator.comparingLong(q -> q.getValue()))
                .map(e -> e.getKey()).orElse(null);
		
		return mostFrequentDonor;
	}
	
	private String calculateMostDonatedItem(List<String> itemNames) {
		String mostDonatedItem = itemNames.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet()
                .stream().max(java.util.Comparator.comparingLong(q -> q.getValue()))
                .map(e -> e.getKey()).orElse(null);
		
		return mostDonatedItem;
	}

	private static void setMinOrMax(DonationStatisticCollection statCollection, Donation donation) {
		if(donation.getDonationWeight() < statCollection.getMinWeight()) {
			statCollection.setMinWeight(donation.getDonationWeight());
		}
		
		if(donation.getDonationWeight() > statCollection.getMaxWeight()) {
			statCollection.setMaxWeight(donation.getDonationWeight());
		}
	}

}
