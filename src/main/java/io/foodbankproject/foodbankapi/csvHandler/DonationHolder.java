package io.foodbankproject.foodbankapi.csvHandler;

import io.foodbankproject.foodbankapi.entity.Donation;

public class DonationHolder {

	private int donationId;
	
	private String dateReceived;

	private String donorName, donorEmail, donorAddress;
	private int donationWeight;
	
	public DonationHolder(Donation d) {
		this.donationId = d.getDonationId();
		this.dateReceived = d.getDateReceived();
		this.donorName = d.getDonorName();
		this.donorEmail = d.getDonorEmail();
		this.donorAddress = d.getDonorAddress();
		this.donationWeight = d.getDonationWeight();
	}

	public int getDonationId() {
		return donationId;
	}

	public void setDonationId(int donationId) {
		this.donationId = donationId;
	}

	public String getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(String dateReceived) {
		this.dateReceived = dateReceived;
	}

	public String getDonorName() {
		return donorName;
	}

	public void setDonorName(String donorName) {
		this.donorName = donorName;
	}

	public String getDonorEmail() {
		return donorEmail;
	}

	public void setDonorEmail(String donorEmail) {
		this.donorEmail = donorEmail;
	}

	public String getDonorAddress() {
		return donorAddress;
	}

	public void setDonorAddress(String donorAddress) {
		this.donorAddress = donorAddress;
	}

	public int getDonationWeight() {
		return donationWeight;
	}

	public void setDonationWeight(int donationWeight) {
		this.donationWeight = donationWeight;
	}
	
}
