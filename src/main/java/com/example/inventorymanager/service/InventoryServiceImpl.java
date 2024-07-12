package com.example.inventorymanager.service;

import com.example.inventorymanager.entity.Inventory;
import com.example.inventorymanager.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    @Override
    public Optional<Inventory> getInventoryById(Long id) {
        return inventoryRepository.findById(id);
    }

    @Override
    public Inventory updateInventory(Long id, Inventory updatedInventory) {
        if (inventoryRepository.existsById(id)) {
            updatedInventory.setId(id);
            return inventoryRepository.save(updatedInventory);
        }
        return null; // or throw custom exception
    }

    @Override
    public boolean deleteInventory(Long id) {
        if (inventoryRepository.existsById(id)) {
            inventoryRepository.deleteById(id);
            return true;
        }
        return false; // or throw custom exception
    }
}
