package com.inn.warehouses.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.warehouses.entities.Warehouse;
import com.inn.warehouses.repositories.WarehouseRepository;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    public List<Warehouse> findAll() {
        return warehouseRepository.findAll();
    }

    public Optional<Warehouse> findById(Long id) {
        return warehouseRepository.findById(id);
    }

    public Warehouse save(Warehouse Warehouse) {
        return warehouseRepository.save(Warehouse);
    }

    public void deleteById(Long id) {
    	warehouseRepository.deleteById(id);
    }
}