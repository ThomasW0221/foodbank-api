package io.foodbankproject.foodbankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.foodbankproject.foodbankapi.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{

}
