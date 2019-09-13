package io.foodbankproject.foodbankapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.foodbankproject.foodbankapi.entity.Donation;
import io.foodbankproject.foodbankapi.repository.DonationRepository;

@RestController("/donations")
public class DonationController {

	@Autowired
	private DonationRepository donationRepository;
	
	@GetMapping("/getAll")
	public Iterable<Donation> getAll() {
		return donationRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Donation getOne(@PathVariable("id") Integer id) {
		//TODO: Validate that we have an item present at that id
		//Hint, look at methods available on donationRepository
		
		return donationRepository.findById(id).orElse(null);
	}
}
