package io.foodbankproject.foodbankapi.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping("/donations")
	public ResponseEntity<?> getDonations(@RequestParam(name = "donationId", required = false) Integer donationId,
			@RequestParam(name = "fromDate", required = false) String fromDate,
			@RequestParam(name = "toDate", required = false) String toDate,
			@RequestParam(name = "donorName", required = false) String donorName,
			@RequestParam(name = "minWeight", required = false) Integer minWeight,
			@RequestParam(name = "maxWeight", required = false) Integer maxWeight) {

		return getDonationsHelper(donationId, fromDate, toDate, donorName, minWeight, maxWeight);

	}

	private ResponseEntity<?> getDonationsHelper(Integer donationId, String fromDate, String toDate, String donorName,
			Integer minWeight, Integer maxWeight) {
		if (donationId != null) { // donation id passed in
			if (donationRepository.existsById(donationId)) {
				return ResponseEntity.ok(donationRepository.findById(donationId).orElse(null));
			} else {
				return ResponseEntity.notFound().build();
			}
		} else if (donorName != null && fromDate != null) { // donor name and from date passed in
			if (toDate != null) { // donor name with to date and from date
				return ResponseEntity.ok(donationRepository.findByNameFromDateAndToDate(donorName, fromDate, toDate));
			}
			return ResponseEntity.ok(donationRepository.findByNameAndFromDate(donorName, fromDate));
		} else if (donorName != null) { // just donor name passed in
			return ResponseEntity.ok(donationRepository.findByName(donorName));
		} else if (fromDate != null) { // from date passed in
			if (toDate != null) { // from date and to date passed in
				return ResponseEntity.ok(donationRepository.findByFromAndToDate(fromDate, toDate));
			} else {
				return ResponseEntity.ok(donationRepository.findByFromDate(fromDate));
			}
		} else if (minWeight != null && maxWeight != null) { // min and max weight passed in
			return ResponseEntity.ok(donationRepository.findByWeightRange(minWeight, maxWeight));
		} else {
			return ResponseEntity.ok(donationRepository.findAll());
		}
	}

	/**
	 * <p>
	 * Accepts a Donation object to be submitted through and HTTP POST request and
	 * adds it to the table of donation entity objects. Also adds the items to the
	 * item entity table. Lastly, it updates the InventoryItem table with new counts
	 * </p>
	 * 
	 * @param donation donation object that is submitted through HTTP POST
	 */
	@PostMapping("/donations")
	public void addDonation(@RequestBody Donation donation) {
		for (Item item : donation.getItemsDonated()) {
			item.setDonation(donation);
		}
		donationRepository.save(donation);

		addToInventory(donation.getItemsDonated());
	}

	private void addToInventory(List<Item> itemList) {
		for (Item item : itemList) {
			String itemName = item.getName();
			if (inventoryItemRepository.existsById(itemName)) {
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
