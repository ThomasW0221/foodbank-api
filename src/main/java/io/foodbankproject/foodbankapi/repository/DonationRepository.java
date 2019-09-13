package io.foodbankproject.foodbankapi.repository;

import org.springframework.data.repository.CrudRepository;

import io.foodbankproject.foodbankapi.entity.Donation;

public interface DonationRepository extends CrudRepository<Donation, Integer>{

}
