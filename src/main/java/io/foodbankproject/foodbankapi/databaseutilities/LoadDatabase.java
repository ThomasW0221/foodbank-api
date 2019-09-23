package io.foodbankproject.foodbankapi.databaseutilities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import io.foodbankproject.foodbankapi.entity.Donation;
import io.foodbankproject.foodbankapi.entity.Item;
import io.foodbankproject.foodbankapi.repository.DonationRepository;

@Configuration
public class LoadDatabase {

	@Bean
	CommandLineRunner run(DonationRepository donationRepository) {
		return args -> {
			// Load Donation 1
			Item item1d1 = new Item("Can of beans", "kidney beans", false, 2);
			Item item2d1 = new Item("Bananas", "1 bundle of bananas", true, 2);
			List<Item> d1Items = new ArrayList<>();
			d1Items.add(item1d1);
			d1Items.add(item2d1);
			Donation d1 = new Donation("9/12/2019", d1Items);
			for(Item item : d1.getItemsDonated()){
				item.setDonation(d1);
			}
			donationRepository.save(d1);
			
			// Load Donation 2
			Item item1d2 = new Item("skinless chicken breast", "purdue", true, 2);
			Item item2d2 = new Item("cheerios", "1 box", false, 1);
			Item item3d2 = new Item("lox", "salted smoked salmon", true, 2);
			List<Item> d2Items = new ArrayList<>();
			d2Items.add(item1d2);
			d2Items.add(item2d2);
			d2Items.add(item3d2);
			Donation d2 = new Donation("9/13/2019", d2Items);
			for(Item item : d2.getItemsDonated()){
				item.setDonation(d2);
			}
			donationRepository.save(d2);
		};
	}
}
