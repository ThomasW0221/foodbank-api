package io.foodbankproject.foodbankapi.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InventoryItem {

	@Id
	private String foodItemName;
	
	private int foodItemQuantity;
	
	public InventoryItem() {
		
	}

	public InventoryItem(String foodItemName, int foodItemQuantity) {
		this.foodItemName = foodItemName;
		this.foodItemQuantity = foodItemQuantity;
	}

	public synchronized String getFoodItemName() {
		return foodItemName;
	}

	public synchronized void setFoodItemName(String foodItemName) {
		this.foodItemName = foodItemName;
	}

	public synchronized int getFoodItemQuantity() {
		return foodItemQuantity;
	}

	public synchronized void setFoodItemQuantity(int foodItemQuantity) {
		this.foodItemQuantity = foodItemQuantity;
	}

	@Override
	public String toString() {
		return "Inventory [foodItemName=" + foodItemName + ", foodItemQuantity=" + foodItemQuantity + "]";
	}	
	
}
