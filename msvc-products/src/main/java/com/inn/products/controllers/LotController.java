package com.inn.products.controllers;

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

import com.inn.commons.exceptions.ResourceNotFoundException;
import com.inn.products.config.RequiresRoles;
import com.inn.products.dtos.LotDTO;
import com.inn.products.entities.Lot;
import com.inn.products.services.LotService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products/lots")
public class LotController {

    @Autowired
    private LotService lotService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public List<LotDTO> getAllLot() {
        return lotService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<LotDTO> getLotById(@PathVariable Long id) {
        Lot lot = lotService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lot not found for this id :: " + id));
        return ResponseEntity.ok(convertToDTO(lot));
    }

    @PostMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public LotDTO createLot(@Valid @RequestBody LotDTO lotDTO) {
        Lot lot = convertToEntity(lotDTO);
        return convertToDTO(lotService.save(lot));
    }

    @PutMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<LotDTO> updateLot(@PathVariable Long id, @Valid @RequestBody LotDTO lotDTO) {
        Lot lot = lotService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lot not found for this id :: " + id));
        modelMapper.map(lotDTO, lot);
        return ResponseEntity.ok(convertToDTO(lotService.save(lot)));
    }

    @DeleteMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteLot(@PathVariable Long id) {
        lotService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lot not found for this id :: " + id));
        lotService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private LotDTO convertToDTO(Lot lot) {
        return modelMapper.map(lot, LotDTO.class);
    }

    private Lot convertToEntity(LotDTO lotDTO) {
        return modelMapper.map(lotDTO, Lot.class);
    }
}