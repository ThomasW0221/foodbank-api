package io.foodbankproject.foodbankapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.foodbankproject.foodbankapi.entity.Item;
import io.foodbankproject.foodbankapi.repository.ItemRepository;

@RestController
public class ItemController {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@GetMapping("/items")
	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}
	
	@GetMapping("/items/{itemId}")
	public Item getOneItem(@PathVariable("itemId") Integer itemId) {
		return itemRepository.findById(itemId).orElse(null);
	}
	
	@GetMapping("/items/byDonation/{donationId}")
	public List<Item> getItemsByDonationId(@PathVariable("donationId") Integer donationId){
		return  itemRepository.findByDonation(donationId);
	}
	
	// Probably do not want to delete items 
	@DeleteMapping("/items/{itemId}")
	public void deleteItem(@PathVariable("itemId") Integer itemId) {
		itemRepository.deleteById(itemId);
	}
}
