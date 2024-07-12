package com.example.inventorymanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.inventorymanager.Utils.Constants;
import com.example.inventorymanager.Utils.JwtUtils;
import com.example.inventorymanager.entity.Inventory;
import com.example.inventorymanager.service.InventoryService;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // Create operation
    @PostMapping("/create")
    public ResponseEntity<?> createInventory(@Valid @RequestBody Inventory inventory,
    		@RequestHeader(value ="Authorization", required = true)String jwt) {
    	Inventory inventory2 = null;
    	String token = JwtUtils.trimBearerToken(jwt);
    	if(!token.equalsIgnoreCase(Constants.SECRET_VALUE)) {
    		return new ResponseEntity<>("Invalid Authorization Token",HttpStatus.UNAUTHORIZED);
    	}
    	inventoryService.createInventory(inventory);
    	return new ResponseEntity<>("Created inventory item",HttpStatus.CREATED);
      
    }

    // Read operations
    @GetMapping("/fetch") 
    public ResponseEntity<?> getAllInventories(@RequestHeader(value ="Authorization", required = true)String jwt) {
    	List<Inventory> allInventory = new ArrayList<>();
    	String token = JwtUtils.trimBearerToken(jwt);
    	if(!token.equalsIgnoreCase(Constants.SECRET_VALUE)) {
    		return new ResponseEntity<>("Invalid Authorization Token",HttpStatus.UNAUTHORIZED);
    	}
    	allInventory = inventoryService.getAllInventories();
        return new ResponseEntity<>(allInventory,HttpStatus.OK);
    }

    @GetMapping("inventory-id/{id}")              
    public ResponseEntity<?> getInventoryById(@PathVariable("id") Long id,
    		@RequestHeader(value ="Authorization", required = true)String jwt) {
    	String token = JwtUtils.trimBearerToken(jwt);
    	if(!token.equalsIgnoreCase(Constants.SECRET_VALUE)) {
    		return new ResponseEntity<>("Invalid Authorization Token",HttpStatus.UNAUTHORIZED);
    	}
    	
    	Optional<Inventory> inventory = inventoryService.getInventoryById(id);
    	if(inventory.isEmpty()) {
    		return new ResponseEntity<>("There is no item with requested id present",HttpStatus.NOT_FOUND);         
    	}
        return new ResponseEntity<>(inventory,HttpStatus.OK);
    }

    // Update operation
    @PutMapping("update/{id}")
    public ResponseEntity<?> updateInventory(@PathVariable("id") Long id, @Valid @RequestBody Inventory inventory,
    		@RequestHeader(value ="Authorization", required = true) String jwt){
    	String token = JwtUtils.trimBearerToken(jwt);
    	if(!token.equalsIgnoreCase(Constants.SECRET_VALUE)) {
    		return new ResponseEntity<>("Invalid Authorization Token",HttpStatus.UNAUTHORIZED);
    	}
    	inventoryService.updateInventory(id, inventory);
        return new ResponseEntity<>("Updated the requested id item",HttpStatus.OK);
    }

    // Delete operation
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteInventory(@PathVariable("id") Long id,
    		@RequestHeader(value ="Authorization", required = true)String jwt) {
    	String token = JwtUtils.trimBearerToken(jwt);
    	if(!token.equalsIgnoreCase(Constants.SECRET_VALUE)) {
    		return new ResponseEntity<>("Invalid Authorization Token",HttpStatus.UNAUTHORIZED);
    	}
        inventoryService.deleteInventory(id);
        return new ResponseEntity<>("Deleted the requested item",HttpStatus.NO_CONTENT);
    }
}
