package io.foodbankproject.foodbankapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Item {

	private @Id @GeneratedValue Integer itemId;
	
	private String name;
	
	private String description;
	
	private int itemCount;
	
	@ManyToOne
	@JoinColumn(name="donation_id")
	@JsonBackReference
	private Donation donation;
	
	public Item() {
		
	}

	public Item(String name, String description, int itemCount) {
		this.name = name;
		this.description = description;
		this.itemCount = itemCount;
	}

	public synchronized Integer getItemId() {
		return itemId;
	}

	public synchronized void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public synchronized String getName() {
		return name;
	}

	public synchronized void setName(String name) {
		this.name = name;
	}

	public synchronized String getDescription() {
		return description;
	}

	public synchronized void setDescription(String description) {
		this.description = description;
	}

	public synchronized Donation getDonation() {
		return donation;
	}

	public synchronized void setDonation(Donation donation) {
		this.donation = donation;
	}

	public synchronized int getItemCount() {
		return itemCount;
	}

	public synchronized void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", name=" + name + ", description=" + description + ", itemCount=" + itemCount
				+ ", donation=" + donation + "]";
	}
		
}
