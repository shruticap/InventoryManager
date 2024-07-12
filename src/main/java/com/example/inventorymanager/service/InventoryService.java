package com.example.inventorymanager.service;

import com.example.inventorymanager.entity.Inventory;

import java.util.List;
import java.util.Optional;

public interface InventoryService {

    Inventory createInventory(Inventory inventory);

    List<Inventory> getAllInventories();

    Optional<Inventory> getInventoryById(Long id);

    Inventory updateInventory(Long id, Inventory inventory);

    boolean deleteInventory(Long id);
}
