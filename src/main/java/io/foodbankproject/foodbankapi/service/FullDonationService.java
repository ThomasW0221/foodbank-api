package io.foodbankproject.foodbankapi.service;

import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.foodbankproject.foodbankapi.entity.Donation;
import io.foodbankproject.foodbankapi.repository.DonationRepository;
import io.foodbankproject.foodbankapi.repository.InventoryItemRepository;
import io.foodbankproject.foodbankapi.repository.ItemRepository;

@Service
public class FullDonationService {

	private DonationRepository donationRepository;
	
	private InventoryItemRepository inventoryItemRepository;
	
	private ItemRepository itemRepository;
	
	private ReentrantReadWriteLock readWriteLock;

	@Autowired
	public FullDonationService(DonationRepository donationRepository, InventoryItemRepository inventoryItemRepository,
			ItemRepository itemRepository) {
		this.donationRepository = donationRepository;
		this.inventoryItemRepository = inventoryItemRepository;
		this.itemRepository = itemRepository;
		this.readWriteLock = new ReentrantReadWriteLock();
	}
	
	// Donation Get Methods
	public boolean donationExistsById(Integer id) {
		return donationRepository.existsById(id);
	}
	
	@Transactional(readOnly=true)
	public Donation donationFindById(Integer donationId) {
		readWriteLock.readLock().lock();
		Donation d;
		try {
			d = donationRepository.findById(donationId).orElse(null);
		} finally {
			readWriteLock.readLock().unlock();
		}
		
		return d;
	}
	
	@Transactional(readOnly=true)
	public List<Donation> donationFindByNameFromDateAndToDate(String name, String fromDate, String toDate) {
		readWriteLock.readLock().lock();
		List<Donation> d;
		try {
			d = donationRepository.findByNameFromDateAndToDate(name, fromDate, toDate);
		} finally {
			readWriteLock.readLock().unlock();
		}
		return d;
	}
	
	@Transactional(readOnly=true)
	public List<Donation> donationFindByNameAndFromDate(String donorName, String fromDate) {
		readWriteLock.readLock().lock();
		List<Donation> d;
		try {
			d = donationRepository.findByNameAndFromDate(donorName, fromDate);
		} finally {
			readWriteLock.readLock().unlock();
		}
		return d;
	}
	
	
}
