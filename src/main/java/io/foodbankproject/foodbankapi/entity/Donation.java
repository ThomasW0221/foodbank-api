package io.foodbankproject.foodbankapi.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Donation {

	@Id
	@Column
	private Integer donationId;
	
	@Column
	private String dateReceived;

	public Donation() {
		// empty constructor
	}
	
	public Donation(Integer donationId, String dateReceived) {
		this.donationId = donationId;
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
	
//	@Column
// 	One to Many mapping
//	private List<Item> items;
	
}
