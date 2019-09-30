package io.foodbankproject.foodbankapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.foodbankproject.foodbankapi.entity.InventoryItem;
import io.foodbankproject.foodbankapi.entity.InventoryItemWrapper;
import io.foodbankproject.foodbankapi.repository.InventoryItemRepository;

@RestController
public class InventoryItemController {
	
	@Autowired
	private InventoryItemRepository inventoryItemRepository;

	@GetMapping("/inventory")
	public ResponseEntity<?> getInventoryItems(@RequestParam(name="itemName", required=false, defaultValue="null")
		String itemName){
		
		if(itemName.equals("null")) {
			return ResponseEntity.ok(inventoryItemRepository.findAll());
		} else {
			if(inventoryItemRepository.existsById(itemName)) {
				return ResponseEntity.ok(inventoryItemRepository.findById(itemName));
			} else {
				return ResponseEntity.notFound().build();
			}
		}
	}
	
	@PostMapping("/inventory")
	public ResponseEntity<?> updateInventoryItemCounts(@RequestBody InventoryItemWrapper itemList){
		for(InventoryItem item : itemList.getInventoryItemList()) {
			if(inventoryItemRepository.existsById(item.getFoodItemName())) {
				InventoryItem itemToModify = inventoryItemRepository.findById(item.getFoodItemName()).orElse(null);
				int newQuantity = itemToModify.getFoodItemQuantity()- item.getFoodItemQuantity();
				if(newQuantity == 0) {
					inventoryItemRepository.delete(itemToModify);
					continue;
				}
				itemToModify.setFoodItemQuantity(newQuantity);
				inventoryItemRepository.save(itemToModify);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(item.getFoodItemName() + 
						" was not found in the database. Please correct the request, resubmit the item"
						+ " that caused an error, and all the items after that.");
			}
		}
		return ResponseEntity.ok("Counts were updated successfully");
	}
	
	
}
