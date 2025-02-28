package com.inn.entities.controllers;

import com.inn.entities.config.RequiresRoles;
import com.inn.entities.dtos.EntitiesTypeDTO;
import com.inn.entities.entities.EntitiesType;
import com.inn.entities.exceptions.ResourceNotFoundException;
import com.inn.entities.services.EntitiesTypeService;

import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/entities/types")
public class EntitiesTypeController {

    @Autowired
    private EntitiesTypeService entitiesTypeService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public List<EntitiesTypeDTO> getAllEntitiesTypes() {
        return entitiesTypeService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<EntitiesTypeDTO> getEntitiesTypeById(@PathVariable Long id) {
        EntitiesType entitiesType = entitiesTypeService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EntitiesType not found for this id :: " + id));
        return ResponseEntity.ok(convertToDTO(entitiesType));
    }

    @PostMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public EntitiesTypeDTO createEntitiesType(@Valid @RequestBody EntitiesTypeDTO entitiesTypeDTO) {
        EntitiesType entitiesType = convertToEntity(entitiesTypeDTO);
        return convertToDTO(entitiesTypeService.save(entitiesType));
    }

    @PutMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<EntitiesTypeDTO> updateEntitiesType(@PathVariable Long id, @Valid @RequestBody EntitiesTypeDTO entitiesTypeDTO) {
        EntitiesType entitiesType = entitiesTypeService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EntitiesType not found for this id :: " + id));
        modelMapper.map(entitiesTypeDTO, entitiesType);
        return ResponseEntity.ok(convertToDTO(entitiesTypeService.save(entitiesType)));
    }

    @DeleteMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteEntitiesType(@PathVariable Long id) {
        entitiesTypeService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EntitiesType not found for this id :: " + id));
        entitiesTypeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private EntitiesTypeDTO convertToDTO(EntitiesType entitiesType) {
        return modelMapper.map(entitiesType, EntitiesTypeDTO.class);
    }

    private EntitiesType convertToEntity(EntitiesTypeDTO entitiesTypeDTO) {
        return modelMapper.map(entitiesTypeDTO, EntitiesType.class);
    }
}