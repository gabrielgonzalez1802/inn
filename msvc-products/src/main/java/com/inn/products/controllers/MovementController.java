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

import com.inn.commons.dtos.MovementDTO;
import com.inn.commons.enums.TipoMovimiento;
import com.inn.commons.exceptions.ResourceNotFoundException;
import com.inn.products.config.RequiresRoles;
import com.inn.products.dtos.ProductList;
import com.inn.products.entities.Movement;
import com.inn.products.services.MovementService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products/movements")
public class MovementController {

    @Autowired
    private MovementService movementService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public List<MovementDTO> getAllMovement() {
        return movementService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<MovementDTO> getMovementById(@PathVariable Long id) {
        Movement movement = movementService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movement not found for this id :: " + id));
        return ResponseEntity.ok(convertToDTO(movement));
    }

    @PostMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public MovementDTO createMovement(@Valid @RequestBody MovementDTO movementDTO) {
        Movement movement = convertToEntity(movementDTO);
        return convertToDTO(movementService.save(movement));
    }
    
    @PostMapping("/list-products")
    @RequiresRoles({"ROLE_ADMIN"})
    public List<MovementDTO> createMovementByListProducts(@Valid @RequestBody ProductList listProducts) {
		return movementService.saveByListProducts(listProducts).stream().map(this::convertToDTO)
				.collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<MovementDTO> updateMovement(@PathVariable Long id, @Valid @RequestBody MovementDTO movementDTO) {
        Movement movement = movementService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movement not found for this id :: " + id));
        modelMapper.map(movementDTO, movement);
        return ResponseEntity.ok(convertToDTO(movementService.save(movement)));
    }

    @DeleteMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteMovement(@PathVariable Long id) {
        movementService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movement not found for this id :: " + id));
        movementService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private MovementDTO convertToDTO(Movement movement) {
    	MovementDTO movimentDTO = modelMapper.map(movement, MovementDTO.class);
    	movimentDTO.setMovementType(TipoMovimiento.fromValor(movement.getMovementTypeId()));
        return movimentDTO;
    }

    private Movement convertToEntity(MovementDTO movementDTO) {
        return modelMapper.map(movementDTO, Movement.class);
    }
}