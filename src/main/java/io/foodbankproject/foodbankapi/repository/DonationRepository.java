package io.foodbankproject.foodbankapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.foodbankproject.foodbankapi.entity.Donation;

public interface DonationRepository extends JpaRepository<Donation, Integer>{

	@Query(value="SELECT * FROM donation WHERE date_received >= :fromDate AND date_received <= CURDATE()",
			nativeQuery=true)
	List<Donation> findByFromDate(String fromDate);

	@Query(value="SELECT * FROM donation WHERE date_received >= :fromDate AND date_received <= :toDate",
			nativeQuery=true)
	List<Donation> findByFromAndToDate(String fromDate, String toDate);
	
	@Query(value="SELECT * FROM donation WHERE date_received >= :fromDate AND date_received <= CURDATE() AND donor_name = :name",
			nativeQuery=true)
	List<Donation> findByNameAndFromDate(String name, String fromDate);
	
	@Query(value="SELECT * FROM donation WHERE date_received >= :fromDate AND date_received <= :toDate AND donor_name = :name",
			nativeQuery=true)
	List<Donation> findByNameFromDateAndToDate(String name, String fromDate, String toDate);

	@Query(value="SELECT * FROM donation WHERE donor_name = :donorName", nativeQuery=true)
	List<Donation> findByName(String donorName);

	@Query(value="SELECT * FROM donation WHERE donation_weight >= :minWeight AND donation_weight <= :maxWeight",
			nativeQuery=true)
	List<Donation> findByWeightRange(Integer minWeight, Integer maxWeight);
}
