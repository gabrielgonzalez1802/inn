package com.inn.warehouses.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.commons.dtos.AddressDTO;
import com.inn.commons.exceptions.ResourceNotFoundException;
import com.inn.warehouses.clients.AddressClientRest;
import com.inn.warehouses.entities.Warehouse;
import com.inn.warehouses.repositories.WarehouseRepository;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;
    
    @Autowired
    private AddressClientRest addressClientFeign;

    public List<Warehouse> findAll() {
        return warehouseRepository.findAll();
    }

    public Optional<Warehouse> findById(Long id) {
        return warehouseRepository.findById(id);
    }

    public Warehouse save(Warehouse Warehouse) {
    	
    	if(Warehouse.getAddressId()!=null) {
    		AddressDTO address =  addressClientFeign.getAddressById(Warehouse.getAddressId());
			if (address != null) {
		        return warehouseRepository.save(Warehouse);
    		}else {
    			throw new ResourceNotFoundException("Address With id " + Warehouse.getAddressId() + " not found");
    		}
    	}
    	
        return warehouseRepository.save(Warehouse);
    }

    public void deleteById(Long id) {
    	warehouseRepository.deleteById(id);
    }
}