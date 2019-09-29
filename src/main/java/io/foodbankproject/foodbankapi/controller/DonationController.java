package io.foodbankproject.foodbankapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.foodbankproject.foodbankapi.entity.Donation;
import io.foodbankproject.foodbankapi.entity.InventoryItem;
import io.foodbankproject.foodbankapi.entity.Item;
import io.foodbankproject.foodbankapi.repository.DonationRepository;
import io.foodbankproject.foodbankapi.repository.InventoryItemRepository;

@RestController
public class DonationController {

	@Autowired
	private DonationRepository donationRepository;
	
	@Autowired
	private InventoryItemRepository inventoryItemRepository;
	
	/**
	 * Returns the list of all donations when an HTTP GET request is made to the /donations endpoint
	 * 
	 * @return The list of donation objects
	 */
	@GetMapping("/donations")
	public List<Donation> getAllDonations() {
		return donationRepository.findAll();
	}
	
	/**
	 * <p>Returns the single donation object based on ID, when an HTTP GET request is made to the /donations endpoint.
	 * This method will be refactored in the near future.</p>
	 * 
	 * @param id id of entity
	 * @return
	 */
	@GetMapping("donations/{id}")
	public Donation getOneDonation(@PathVariable("id") Integer id) {
		//TODO: Validate that we have an item present at that id
		//Hint, look at methods available on donationRepository
		
		return donationRepository.findById(id).orElse(null);
	}
	
	/**
	 * <p>Accepts a Donation object to be submitted through and HTTP POST request and adds it to
	 * the table of donation entity objects. Also adds the items to the item entity table.
	 * Lastly, it updates the InventoryItem table with new counts</p>
	 * 
	 * @param donation donation object that is submitted through HTTP POST
	 */
	@PostMapping("/donations")
	public void addDonation(@RequestBody Donation donation) {
		for(Item item : donation.getItemsDonated()){
			item.setDonation(donation);
		}
		donationRepository.save(donation);
		
		addToInventory(donation.getItemsDonated());
	}
	
	private void addToInventory(List<Item> itemList) {
		for(Item item: itemList) {
			String itemName = item.getName();
			if(inventoryItemRepository.existsById(itemName)) {
				InventoryItem inventoryItem = inventoryItemRepository.findById(itemName).orElse(null);
				int inventoryItemCurrentQuantity = inventoryItem.getFoodItemQuantity();
				inventoryItem.setFoodItemQuantity(inventoryItemCurrentQuantity + item.getItemCount());
				inventoryItemRepository.save(inventoryItem);
			} else {
				InventoryItem inventoryItem = new InventoryItem(item.getName(), item.getItemCount());
				inventoryItemRepository.save(inventoryItem);
			}
		}
	}
	
}
