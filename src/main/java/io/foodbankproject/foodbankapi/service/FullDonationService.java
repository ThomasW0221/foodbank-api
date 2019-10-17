package io.foodbankproject.foodbankapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.foodbankproject.foodbankapi.entity.Donation;
import io.foodbankproject.foodbankapi.entity.InventoryItem;
import io.foodbankproject.foodbankapi.entity.Item;
import io.foodbankproject.foodbankapi.repository.DonationRepository;
import io.foodbankproject.foodbankapi.repository.InventoryItemRepository;
import io.foodbankproject.foodbankapi.repository.ItemRepository;

@Service
public class FullDonationService {

	private DonationRepository donationRepository;

	private InventoryItemRepository inventoryItemRepository;

	private ItemRepository itemRepository;

	@Autowired
	public FullDonationService(DonationRepository donationRepository, InventoryItemRepository inventoryItemRepository,
			ItemRepository itemRepository) {
		this.donationRepository = donationRepository;
		this.inventoryItemRepository = inventoryItemRepository;
		this.itemRepository = itemRepository;
	}

	// All Repository Check Methods
	@Transactional(readOnly = true)
	public boolean donationExistsById(Integer id) {

		return donationRepository.existsById(id);
	}

	@Transactional(readOnly = true)
	public boolean inventoryItemExistsById(String itemName) {
		return inventoryItemRepository.existsById(itemName);
	}

	@Transactional(readOnly = true)
	public boolean itemExistsById(Integer id) {
		return itemRepository.existsById(id);
	}

	// Donation Read Methods
	@Transactional(readOnly = true)
	public Donation donationFindById(Integer donationId) {

		return donationRepository.findById(donationId).orElse(null);
	}

	@Transactional(readOnly = true)
	public List<Donation> donationFindByNameFromDateAndToDate(String name, String fromDate, String toDate) {

		return donationRepository.findByNameFromDateAndToDate(name, fromDate, toDate);
	}

	@Transactional(readOnly = true)
	public List<Donation> donationFindByNameAndFromDate(String donorName, String fromDate) {

		return donationRepository.findByNameAndFromDate(donorName, fromDate);
	}

	@Transactional(readOnly = true)
	public List<Donation> donationFindByName(String name) {

		return donationRepository.findByName(name);
	}

	@Transactional(readOnly = true)
	public List<Donation> donationFindByFromAndToDate(String fromDate, String toDate) {

		return donationRepository.findByFromAndToDate(fromDate, toDate);
	}

	@Transactional(readOnly = true)
	public List<Donation> donationFindByFromDate(String fromDate) {

		return donationRepository.findByFromDate(fromDate);
	}

	@Transactional(readOnly = true)
	public List<Donation> donationFindByWeightRange(Integer minWeight, Integer maxWeight) {

		return donationRepository.findByWeightRange(minWeight, maxWeight);

	}

	// Donation Write Methods
	@Transactional
	public void saveDonation(Donation donation) {

		donationRepository.save(donation);

	}

	// Inventory Item Read Methods
	@Transactional(readOnly = true)
	public InventoryItem inventoryItemFindById(String itemName) {

		return inventoryItemRepository.findById(itemName).orElse(null);
	}

	@Transactional(readOnly = true)
	public List<InventoryItem> inventoryItemFindAll() {

		return inventoryItemRepository.findAll();

	}

	// Inventory Item Write Methods
	@Transactional
	public void saveInventoryItem(InventoryItem ii) {

		inventoryItemRepository.save(ii);
	}

	@Transactional
	public void deleteInventoryItem(InventoryItem ii) {
		inventoryItemRepository.delete(ii);
	}

	// Inventory Read Methods
	@Transactional(readOnly = true)
	public Item itemFindById(Integer id) {

		return itemRepository.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public List<Item> itemFindByDonationId(Integer donationId) {

		return itemRepository.findByDonation(donationId);

	}

	@Transactional(readOnly = true)
	public List<Item> itemFindByName(String itemName) {

		return itemRepository.findByName(itemName);

	}

}
