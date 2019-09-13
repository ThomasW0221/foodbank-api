package io.foodbankproject.foodbankapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.foodbankproject.foodbankapi.entity.Donation;
import io.foodbankproject.foodbankapi.entity.Item;
import io.foodbankproject.foodbankapi.repository.DonationRepository;

@RestController()
public class DonationController {

	@Autowired
	private DonationRepository donationRepository;
	
	@GetMapping("/donations")
	public List<Donation> getAllDonations() {
		return donationRepository.findAll();
	}
	
	@GetMapping("donations/{id}")
	public Donation getOneDonation(@PathVariable("id") Integer id) {
		//TODO: Validate that we have an item present at that id
		//Hint, look at methods available on donationRepository
		
		return donationRepository.findById(id).orElse(null);
	}
	
	@PostMapping()
	public void addDonation(@RequestBody Donation donation) {
		for(Item item : donation.getItemsDonated()){
			item.setDonation(donation);
		}
		donationRepository.save(donation);
		
	}
	
	
}
