package io.foodbankproject.foodbankapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.foodbankproject.foodbankapi.entity.InventoryItem;
import io.foodbankproject.foodbankapi.repository.InventoryItemRepository;

@RestController
public class InventoryItemController {
	
	@Autowired
	private InventoryItemRepository inventoryItemRepository;

	@GetMapping("/inventory")
	public List<InventoryItem> getAllDonations(){
		return inventoryItemRepository.findAll();
	}
}
