package io.foodbankproject.foodbankapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.foodbankproject.foodbankapi.repository.InventoryItemRepository;

@RestController
public class InventoryItemController {
	
	@Autowired
	private InventoryItemRepository inventoryItemRepository;

	@GetMapping("/inventory")
	public ResponseEntity<?> getDonations(@RequestParam(name="itemName", required=false, defaultValue="null")
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
}
