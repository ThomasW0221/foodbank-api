package io.foodbankproject.foodbankapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.foodbankproject.foodbankapi.entity.Donation;

public interface DonationRepository extends JpaRepository<Donation, Integer>{

	@Query(value="SELECT * FROM DONATION WHERE DATE_RECEIVED >= :fromDate AND DATE_RECEIVED <= CURDATE()",
			nativeQuery=true)
	List<Donation> findByFromDate(String fromDate);

	@Query(value="SELECT * FROM DONATION WHERE DATE_RECEIVED >= :fromDate AND DATE_RECEIVED <= :toDate",
			nativeQuery=true)
	List<Donation> findByFromAndToDate(String fromDate, String toDate);
	
	@Query(value="SELECT * FROM DONATION WHERE DATE_RECEIVED >= :fromDate AND DATE_RECEIVED <= CURDATE() AND DONOR_NAME = :name",
			nativeQuery=true)
	List<Donation> findByNameAndFromDate(String name, String fromDate);
	
	@Query(value="SELECT * FROM DONATION WHERE DATE_RECEIVED >= :fromDate AND DATE_RECEIVED <= :toDate AND DONOR_NAME = :name",
			nativeQuery=true)
	List<Donation> findByNameFromDateAndToDate(String name, String fromDate, String toDate);

	@Query(value="SELECT * FROM DONATION WHERE DONOR_NAME = :donorName", nativeQuery=true)
	List<Donation> findByName(String donorName);

	@Query(value="SELECT * FROM DONATION WHERE DONATION_WEIGHT >= :minWeight AND DONATION_WEIGHT <= :maxWeight",
			nativeQuery=true)
	List<Donation> findByWeightRange(Integer minWeight, Integer maxWeight);
}
