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

	public Donation(String dateReceived) {
		this.dateReceived = dateReceived;
	}

	public Integer getDonationId() {
		return donationId;
	}

	public void setDonationId(Integer donationId) {
		this.donationId = donationId;
	}

	public String getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(String dateReceived) {
		this.dateReceived = dateReceived;
	}
	
	public List<Item> getItemsDonated() {
		return itemsDonated;
	}

	public void setItemsDonated(List<Item> itemsDonated) {
		this.itemsDonated = itemsDonated;
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

	public int getDonorWeight() {
		return donationWeight;
	}

	public void setDonorWeight(int donorWeight) {
		this.donationWeight = donorWeight;
	}

	@Override
	public String toString() {
		return "Donation [donationId=" + donationId + ", dateReceived=" + dateReceived + ", itemsDonated="
				+ itemsDonated + ", donorName=" + donorName + ", donorEmail=" + donorEmail + ", donorAddress="
				+ donorAddress + ", donorWeight=" + donationWeight + "]";
	}
	
	
	
}
