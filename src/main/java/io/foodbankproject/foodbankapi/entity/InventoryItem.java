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

	public String getFoodItemName() {
		return foodItemName;
	}

	public void setFoodItemName(String foodItemName) {
		this.foodItemName = foodItemName;
	}

	public int getFoodItemQuantity() {
		return foodItemQuantity;
	}

	public void setFoodItemQuantity(int foodItemQuantity) {
		this.foodItemQuantity = foodItemQuantity;
	}

	@Override
	public String toString() {
		return "Inventory [foodItemName=" + foodItemName + ", foodItemQuantity=" + foodItemQuantity + "]";
	}	
	
}
