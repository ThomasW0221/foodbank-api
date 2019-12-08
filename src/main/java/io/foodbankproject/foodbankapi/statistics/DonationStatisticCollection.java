package io.foodbankproject.foodbankapi.statistics;

public class DonationStatisticCollection {
	
	private String mostFrequentDonor;
	
	private double minWeight;
	
	private double maxWeight;
	
	private double avgWeight;
	
	private double totalWeight;
	
	private String mostDonatedItem;
	
	private int numberOfItemsDonated;
	
	public DonationStatisticCollection() {
		
	}

	public String getMostFrequentDonor() {
		return mostFrequentDonor;
	}

	public void setMostFrequentDonor(String mostFrequentDonor) {
		this.mostFrequentDonor = mostFrequentDonor;
	}

	public double getMinWeight() {
		return minWeight;
	}

	public void setMinWeight(double minWeight) {
		this.minWeight = minWeight;
	}

	public double getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(double maxWeight) {
		this.maxWeight = maxWeight;
	}

	public double getAvgWeight() {
		return avgWeight;
	}

	public void setAvgWeight(double avgWeight) {
		this.avgWeight = avgWeight;
	}

	public String getMostDonatedItem() {
		return mostDonatedItem;
	}

	public void setMostDonatedItem(String mostDonatedItem) {
		this.mostDonatedItem = mostDonatedItem;
	}

	public int getNumberOfItemsDonated() {
		return numberOfItemsDonated;
	}

	public void setNumberOfItemsDonated(int numberOfItemsDonated) {
		this.numberOfItemsDonated = numberOfItemsDonated;
	}

	public double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(double totalWeight) {
		this.totalWeight = totalWeight;
	}
	
}
