package com.inn.address.controllers;

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

import com.inn.address.entities.EntityAddress;
import com.inn.address.services.EntityAddressService;
import com.inn.commons.config.RequiresRoles;
import com.inn.commons.dtos.EntityAddressDTO;
import com.inn.commons.exceptions.ResourceNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/addresses/entities-adrress")
public class EntityAddressController {

    @Autowired
    private EntityAddressService entityAddressService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public List<EntityAddressDTO> getAllEntityAddresses() {
        return entityAddressService.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    
    @GetMapping("/entities/{entityId}")
    @RequiresRoles({"ROLE_ADMIN"})
    public List<EntityAddressDTO> getAllByEntityId(@PathVariable Long entityId) {
        return entityAddressService.findAllByEntityId(entityId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<EntityAddressDTO> getEntityAddressById(@PathVariable Long id) {
        EntityAddress entityAddress = entityAddressService.findById(id).orElseThrow(() -> new ResourceNotFoundException("EntityAddress not found for this id :: " + id));
        return ResponseEntity.ok(convertToDTO(entityAddress));
    }

    @PostMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public EntityAddressDTO createEntityAddress(@Valid @RequestBody EntityAddressDTO entityAddressDTO) {
        EntityAddress entityAddress = convertToEntity(entityAddressDTO);
        return convertToDTO(entityAddressService.save(entityAddress));
    }

    @PutMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<EntityAddressDTO> updateEntityAddress(@PathVariable Long id, @Valid @RequestBody EntityAddressDTO entityAddressDTO) {
        EntityAddress entityAddress = entityAddressService.findById(id).orElseThrow(() -> new ResourceNotFoundException("EntityAddress not found for this id :: " + id));
        modelMapper.map(entityAddressDTO, entityAddress);
        return ResponseEntity.ok(convertToDTO(entityAddressService.save(entityAddress)));
    }

    @DeleteMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteEntityAddress(@PathVariable Long id) {
        entityAddressService.findById(id).orElseThrow(() -> new ResourceNotFoundException("EntityAddress not found for this id :: " + id));
        entityAddressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private EntityAddressDTO convertToDTO(EntityAddress entityAddress) {
        return modelMapper.map(entityAddress, EntityAddressDTO.class);
    }

    private EntityAddress convertToEntity(EntityAddressDTO entityAddressDTO) {
        return modelMapper.map(entityAddressDTO, EntityAddress.class);
    }
}