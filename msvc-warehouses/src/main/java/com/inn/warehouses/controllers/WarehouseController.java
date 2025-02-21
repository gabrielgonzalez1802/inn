package com.inn.warehouses.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inn.warehouses.dtos.WarehouseDTO;
import com.inn.warehouses.entities.Warehouse;
import com.inn.warehouses.exceptions.ResourceNotFoundException;
import com.inn.warehouses.services.WarehouseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseService WarehouseService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<WarehouseDTO> getAllWarehouse() {
        return WarehouseService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseDTO> getEntityById(@PathVariable Long id) {
        Warehouse entity = WarehouseService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found for this id :: " + id));
        return ResponseEntity.ok(convertToDTO(entity));
    }

    @PostMapping
    public WarehouseDTO createEntity(@Valid @RequestBody WarehouseDTO WarehouseDTO) {
        Warehouse entity = convertToEntity(WarehouseDTO);
        return convertToDTO(WarehouseService.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WarehouseDTO> updateEntity(@PathVariable Long id, @Valid @RequestBody WarehouseDTO WarehouseDTO) {
        Warehouse entity = WarehouseService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found for this id :: " + id));
        modelMapper.map(WarehouseDTO, entity);
        return ResponseEntity.ok(convertToDTO(WarehouseService.save(entity)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable Long id) {
        WarehouseService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found for this id :: " + id));
        WarehouseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private WarehouseDTO convertToDTO(Warehouse entity) {
        return modelMapper.map(entity, WarehouseDTO.class);
    }

    private Warehouse convertToEntity(WarehouseDTO WarehouseDTO) {
        return modelMapper.map(WarehouseDTO, Warehouse.class);
    }
}