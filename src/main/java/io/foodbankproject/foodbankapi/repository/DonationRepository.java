package io.foodbankproject.foodbankapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.foodbankproject.foodbankapi.entity.Donation;

public interface DonationRepository extends JpaRepository<Donation, Integer>{

	@Query(value="SELECT * FROM DONATION WHERE DATE_RECEIVED >= :fromDate AND DATE_RECEIVED < CURDATE()",
			nativeQuery=true)
	List<Donation> findByFromDate(String fromDate);

	@Query(value="SELECT * FROM DONATION WHERE DATE_RECEIVED >= :fromDate AND DATE_RECEIVED <= :toDate",
			nativeQuery=true)
	List<Donation> findByFromAndToDate(String fromDate, String toDate);
}
