package io.foodbankproject.foodbankapi.entity;

import java.util.List;

public class InventoryItemWrapper {

	private List<InventoryItem> inventoryItemList;
	
	public InventoryItemWrapper() {}
	
	public InventoryItemWrapper(List<InventoryItem> inventoryItemList) {
		this.inventoryItemList = inventoryItemList;
	}

	public synchronized List<InventoryItem> getInventoryItemList() {
		return inventoryItemList;
	}

	public synchronized void setInventoryItemList(List<InventoryItem> inventoryItemList) {
		this.inventoryItemList = inventoryItemList;
	}
}
