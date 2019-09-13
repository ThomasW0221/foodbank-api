package io.foodbankproject.foodbankapi.repository;

import org.springframework.data.repository.CrudRepository;

import io.foodbankproject.foodbankapi.entity.Item;

public interface ItemRepository extends CrudRepository<Item, Integer>{

}
