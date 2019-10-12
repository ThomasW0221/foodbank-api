package io.foodbankproject.foodbankapi.service;

import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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
	
	private ReentrantReadWriteLock readWriteLock;

	@Autowired
	public FullDonationService(DonationRepository donationRepository, InventoryItemRepository inventoryItemRepository,
			ItemRepository itemRepository) {
		this.donationRepository = donationRepository;
		this.inventoryItemRepository = inventoryItemRepository;
		this.itemRepository = itemRepository;
		this.readWriteLock = new ReentrantReadWriteLock();
	}
	
	// All Repository Check Methods
	@Transactional(readOnly=true)
	public boolean donationExistsById(Integer id) {
		readWriteLock.readLock().lock();
		boolean b;
		try {
			b = donationRepository.existsById(id);
		} finally {
			readWriteLock.readLock().unlock();
		}
		return b;
	}
	
	@Transactional(readOnly=true)
	public boolean inventoryItemExistsById(String itemName) {
		readWriteLock.readLock().lock();
		boolean b;
		try {
			b = inventoryItemRepository.existsById(itemName);
		} finally {
			readWriteLock.readLock().unlock();
		}
		return b;
	}
	
	@Transactional(readOnly=true)
	public boolean itemExistsById(Integer id) {
		readWriteLock.readLock().lock();
		boolean b;
		try {
			b = itemRepository.existsById(id);
		} finally {
			readWriteLock.readLock().unlock();
		}
		return b;
	}
	
	// Donation Read Methods
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
	
	@Transactional(readOnly=true)
	public List<Donation> donationFindByName(String name) {
		readWriteLock.readLock().lock();
		List<Donation> d;
		try {
			d = donationRepository.findByName(name);
		} finally {
			readWriteLock.readLock().unlock();
		}
		return d;
	}
	
	@Transactional(readOnly=true)
	public List<Donation> donationFindByFromAndToDate(String fromDate, String toDate) {
		readWriteLock.readLock().lock();
		List<Donation> d;
		try {
			d = donationRepository.findByFromAndToDate(fromDate, toDate);
		} finally {
			readWriteLock.readLock().unlock();
		}
		return d;
	}
	
	@Transactional(readOnly=true)
	public List<Donation> donationFindByFromDate(String fromDate) {
		readWriteLock.readLock().lock();
		List<Donation> d;
		try {
			d = donationRepository.findByFromDate(fromDate);
		} finally {
			readWriteLock.readLock().unlock();
		}
		return d;
	}
	
	@Transactional(readOnly=true)
	public List<Donation> donationFindByWeightRange(Integer minWeight, Integer maxWeight) {
		readWriteLock.readLock().lock();
		List<Donation> d;
		try {
			d = donationRepository.findByWeightRange(minWeight, maxWeight);
		} finally {
			readWriteLock.readLock().unlock();
		}
		return d;
	}
	
	// Donation Write Methods
	@Transactional
	public void saveDonation(Donation donation) {
		readWriteLock.writeLock().lock();
		try {
			donationRepository.save(donation);
		} finally {
			readWriteLock.writeLock().unlock();
		}
	}
	
	// Inventory Item Read Methods
	@Transactional(readOnly=true)
	public InventoryItem inventoryItemFindById(String itemName) {
		readWriteLock.readLock().lock();
		InventoryItem ii;
		try {
			ii = inventoryItemRepository.findById(itemName).orElse(null);
		} finally {
			readWriteLock.readLock().unlock();
		}
		return ii;
	}
	
	@Transactional(readOnly=true)
	public List<InventoryItem> inventoryItemFindAll() {
		readWriteLock.readLock().lock();
		List<InventoryItem> ii;
		try {
			ii = inventoryItemRepository.findAll();
		} finally {
			readWriteLock.readLock().unlock();
		}
		
		return ii;
	}
	
	// Inventory Item Write Methods
	@Transactional
	public void saveInventoryItem(InventoryItem ii) {
		readWriteLock.writeLock().lock();
		try {
			inventoryItemRepository.save(ii);
		} finally {
			readWriteLock.writeLock().unlock();
		}
	}
	
	@Transactional
	public void deleteInventoryItem(InventoryItem ii) {
		readWriteLock.writeLock().lock();
		try {
			inventoryItemRepository.delete(ii);
		} finally {
			readWriteLock.writeLock().unlock();
		}
	}
	
	// Inventory Read Methods
	@Transactional(readOnly=true)
	public Item itemFindById(Integer id) {
		readWriteLock.readLock().lock();
		Item i;
		try {
			i = itemRepository.findById(id).orElse(null);
		} finally {
			readWriteLock.readLock().unlock();
		}
		return i;
	}
	
	@Transactional(readOnly=true)
	public List<Item> itemFindByDonationId(Integer donationId) {
		readWriteLock.readLock().lock();
		List<Item> i;
		try {
			i = itemRepository.findByDonation(donationId);
		} finally {
			readWriteLock.readLock().unlock();
		}
		return i;
	}
	
	@Transactional(readOnly=true)
	public List<Item> itemFindByName(String itemName) {
		readWriteLock.readLock().lock();
		List<Item> i;
		try {
			i = itemRepository.findByName(itemName);
		} finally {
			readWriteLock.readLock().unlock();
		}
		return i;
	}
	
}
