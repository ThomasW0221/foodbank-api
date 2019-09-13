package io.foodbankproject.foodbankapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import io.foodbankproject.foodbankapi.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{

	@Query(value="SELECT * FROM ITEM WHERE DONATION_ID = :donationId", nativeQuery=true)
	List<Item> findByDonation(Integer donationId);
}
