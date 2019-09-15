package io.foodbankproject.foodbankapi.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

	public Donation() {
		// empty constructor
	}
	
	public Donation(String dateReceived, List<Item> itemsDonated) {
		this.dateReceived = dateReceived;
		this.itemsDonated = itemsDonated;
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

	@Override
	public String toString() {
		return "Donation [donationId=" + donationId + ", dateReceived=" + dateReceived + ", itemsDonated="
				+ itemsDonated + "]";
	}
	
	
	
}
