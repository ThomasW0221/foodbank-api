package io.foodbankproject.foodbankapi.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import io.foodbankproject.foodbankapi.csvHandler.DonationHolder;
import io.foodbankproject.foodbankapi.entity.Donation;
import io.foodbankproject.foodbankapi.entity.InventoryItem;
import io.foodbankproject.foodbankapi.entity.InventoryItemWrapper;
import io.foodbankproject.foodbankapi.entity.Item;
import io.foodbankproject.foodbankapi.mail.MailHandler;
import io.foodbankproject.foodbankapi.service.FullDonationService;

@RestController
public class DonationController {

	@Autowired
	private FullDonationService fullDonationService;
	
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	private ExecutorService mailExecutor = Executors.newFixedThreadPool(5);

	/**
	 * 
	 * @param donationId donation id accepted from request
	 * @param fromDate date string accepted from request
	 * @param toDate date string accepted from request
	 * @param donorName donor string accepted from request
	 * @param minWeight minimum weight for weight query
	 * @param maxWeight maximum weight for weight query
	 * @return Response entity describing results of desired query, or all donations
	 */
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
		lock.readLock().lock();
		try {
			if (donationId != null) { // donation id passed in
				if (fullDonationService.donationExistsById(donationId)) {
					return ResponseEntity.ok(fullDonationService.donationFindById(donationId));
				} else {
					return ResponseEntity.notFound().build();
				}
			} else if (donorName != null && fromDate != null) { // donor name and from date passed in
				if (toDate != null) { // donor name with to date and from date
					return ResponseEntity.ok(fullDonationService.donationFindByNameFromDateAndToDate(donorName, fromDate, toDate));
				}
				return ResponseEntity.ok(fullDonationService.donationFindByNameAndFromDate(donorName, fromDate));
			} else if (donorName != null) { // just donor name passed in
				return ResponseEntity.ok(fullDonationService.donationFindByName(donorName));
			} else if (fromDate != null) { // from date passed in
				if (toDate != null) { // from date and to date passed in
					return ResponseEntity.ok(fullDonationService.donationFindByFromAndToDate(fromDate, toDate));
				}
				return ResponseEntity.ok(fullDonationService.donationFindByFromDate(fromDate));
			} else if (minWeight != null && maxWeight != null) { // min and max weight passed in
				return ResponseEntity.ok(fullDonationService.donationFindByWeightRange(minWeight, maxWeight));
			} else {
				LocalDateTime time = LocalDateTime.now();
				time = time.minusDays(30);
				String timeString = String.format("%04d-%02d-%02d", time.getYear(), time.getMonthValue(), time.getDayOfMonth());
				return ResponseEntity.ok(fullDonationService.donationFindByFromDate(timeString));
			}
		} finally {
			lock.readLock().unlock();
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
	 * @throws Exception 
	 */
	@PostMapping("/donations")
	public void addDonation(@RequestBody Donation donation) {
		for (Item item : donation.getItemsDonated()) {
			item.setDonation(donation);
		}
		fullDonationService.saveDonation(donation);

		addToInventory(donation.getItemsDonated());
		
		if (isEmailPresent(donation)) {
			submitMailJob(donation);
		}
	}
	
	private boolean isEmailPresent(Donation donation) {
		return donation.getDonorEmail() != null;
	}
	
	private void submitMailJob(Donation donation) {
		MailHandler mailHandler = new MailHandler(donation.getDonorEmail(), donation.getDonorName());
		try {
			mailExecutor.submit(mailHandler);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addToInventory(List<Item> itemList) {
		lock.writeLock().lock();
		try {
			for (Item item : itemList) {
				String itemName = item.getName();
				if (fullDonationService.inventoryItemExistsById(itemName)) {
					InventoryItem inventoryItem = fullDonationService.inventoryItemFindById(itemName);
					int inventoryItemCurrentQuantity = inventoryItem.getFoodItemQuantity();
					inventoryItem.setFoodItemQuantity(inventoryItemCurrentQuantity + item.getItemCount());
					fullDonationService.saveInventoryItem(inventoryItem);
				} else {
					InventoryItem inventoryItem = new InventoryItem(item.getName(), item.getItemCount());
					fullDonationService.saveInventoryItem(inventoryItem);
				}
			}
		} finally {
			lock.writeLock().unlock();
		}
	}
	
	// Inventory Endpoints
	
	@GetMapping("/inventory")
	public ResponseEntity<?> getInventoryItems(@RequestParam(name="itemName", required=false, defaultValue="null")
		String itemName){
		
		return getInventoryItemsHelper(itemName);
	}
	
	private ResponseEntity<?> getInventoryItemsHelper(String itemName) {
		lock.readLock().lock();
		try {
			if(itemName.equals("null")) {
				return ResponseEntity.ok(fullDonationService.inventoryItemFindAll());
			} else {
				if(fullDonationService.inventoryItemExistsById(itemName)) {
					return ResponseEntity.ok(fullDonationService.inventoryItemFindById(itemName));
				} else {
					return ResponseEntity.notFound().build();
				}
			}
		} finally {
			lock.readLock().unlock();
		}
		
	}
	
	@PostMapping("/inventory")
	public ResponseEntity<?> updateInventoryItemCounts(@RequestBody InventoryItemWrapper itemList){
		return removeFromInventory(itemList);
	}
	
	private ResponseEntity<?> removeFromInventory(InventoryItemWrapper itemList) {
		lock.writeLock().lock();
		try {
			for(InventoryItem item : itemList.getInventoryItemList()) {
				if(fullDonationService.inventoryItemExistsById(item.getFoodItemName())) {
					InventoryItem itemToModify = fullDonationService.inventoryItemFindById(item.getFoodItemName());
					int newQuantity = itemToModify.getFoodItemQuantity() - item.getFoodItemQuantity();
					if(newQuantity == 0) {
						fullDonationService.deleteInventoryItem(itemToModify);
						continue;
					}
					itemToModify.setFoodItemQuantity(newQuantity);
					fullDonationService.saveInventoryItem(itemToModify);
				} else {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(item.getFoodItemName() + 
							" was not found in the database. Please correct the request, resubmit the item"
							+ " that caused an error, and all the items after that.");
				}
			}
		} finally {
			lock.writeLock().unlock();
		}
		
		return ResponseEntity.ok().build();
	}
	
	// Item Endpoints
	
	@GetMapping("/items")
	public ResponseEntity<?> getItems(@RequestParam(name="itemId", required=false) Integer itemId,
			@RequestParam(name="donationId", required=false) Integer donationId,
			@RequestParam(name="itemName", required=false) String itemName) {
		
		return getItemsHelper(itemId, donationId, itemName);
	}
	
	private ResponseEntity<?> getItemsHelper(Integer itemId, Integer donationId, String itemName) {
		lock.readLock().lock();
		try {
			if (itemId != null) {
				if(fullDonationService.itemExistsById(itemId)) {
					return ResponseEntity.ok(fullDonationService.itemFindById(itemId));
				} else {
					return ResponseEntity.notFound().build();
				}
			} else if (donationId != null) {
				return ResponseEntity.ok(fullDonationService.itemFindByDonationId(donationId));
			} else if (itemName != null) {
				return ResponseEntity.ok(fullDonationService.itemFindByName(itemName));
			} else {
				return ResponseEntity.badRequest().body("Please use one of the defined queries");
			}
		} finally {
			lock.readLock().unlock();
		}
		
	}
	
	@PostMapping("/csv")
	public void donationsToCsv(@RequestBody List<Donation> donations, HttpServletResponse response) {
        String donationsFileName = "donation.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + donationsFileName + "\"");
        
        List<DonationHolder> modifiedDonations = new ArrayList<>();
        
        for(Donation donation: donations) {
        	DonationHolder holder = new DonationHolder(donation);
        	modifiedDonations.add(holder);
        }
		      
        try {
	        StatefulBeanToCsv<DonationHolder> donationWriter = new StatefulBeanToCsvBuilder<DonationHolder>(response.getWriter())
	                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
	                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
	                .withOrderedResults(false)
	                .build();
	 
	 
	        
	        donationWriter.write(modifiedDonations);
	        
//	        String itemsFileName = "items.csv";
//	
//	        response.setContentType("text/csv");
//	        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
//	                "attachment; filename1=\"" + itemsFileName + "\"");
//			      
//	        StatefulBeanToCsv<Item> itemsWriter = new StatefulBeanToCsvBuilder<Item>(response.getWriter())
//	                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
//	                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
//	                .withOrderedResults(false)
//	                .build();
//	 
//	        ArrayList<Item> itemList = new ArrayList<Item>();
//	        
//	        for (Donation x1 : donations) {
//	        	itemList.addAll(x1.getItemsDonated());
//	        }
//	        
//	        itemsWriter.write(itemList);
     
        } catch (Exception e) {
        	e.printStackTrace();
        }
	
	}

		
        

}
