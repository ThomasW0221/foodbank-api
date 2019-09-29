package io.foodbankproject.foodbankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.foodbankproject.foodbankapi.entity.InventoryItem;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, String>{

}
