package io.foodbankproject.foodbankapi.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Donation {

	private @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Integer donationId;
	
	private String dateReceived;
	
	@OneToMany(mappedBy="donation", cascade= CascadeType.ALL)
	@JsonManagedReference
	private List<Item> itemsDonated;

	private String donorName, donorEmail, donorAddress;
	private int donationWeight;

	public Donation() {
		// empty constructor
	}
	
	public Donation(String dateReceived, List<Item> itemsDonated, String donorName, String donorEmail, String donorAddress, int donationWeight) {
		this.dateReceived = dateReceived;
		this.itemsDonated = itemsDonated;
		this.donorName = donorName;
		this.donorEmail = donorEmail;
		this.donorAddress = donorAddress;
		this.donationWeight = donationWeight;

	}

	public synchronized Integer getDonationId() {
		return donationId;
	}

	public synchronized void setDonationId(Integer donationId) {
		this.donationId = donationId;
	}

	public synchronized String getDateReceived() {
		return dateReceived;
	}

	public synchronized void setDateReceived(String dateReceived) {
		this.dateReceived = dateReceived;
	}
	
	public synchronized List<Item> getItemsDonated() {
		return itemsDonated;
	}

	public synchronized void setItemsDonated(List<Item> itemsDonated) {
		this.itemsDonated = itemsDonated;
	}

	public synchronized String getDonorName() {
		return donorName;
	}

	public synchronized void setDonorName(String donorName) {
		this.donorName = donorName;
	}

	public synchronized String getDonorEmail() {
		return donorEmail;
	}

	public synchronized void setDonorEmail(String donorEmail) {
		this.donorEmail = donorEmail;
	}

	public synchronized String getDonorAddress() {
		return donorAddress;
	}

	public synchronized void setDonorAddress(String donorAddress) {
		this.donorAddress = donorAddress;
	}

	public synchronized int getDonationWeight() {
		return donationWeight;
	}

	public synchronized void setDonationWeight(int donationWeight) {
		this.donationWeight = donationWeight;
	}

	@Override
	public String toString() {
		return "Donation [donationId=" + donationId + ", dateReceived=" + dateReceived + ", itemsDonated="
				+ itemsDonated + ", donorName=" + donorName + ", donorEmail=" + donorEmail + ", donorAddress="
				+ donorAddress + ", donationWeight=" + donationWeight + "]";
	}
	
	
	
}
