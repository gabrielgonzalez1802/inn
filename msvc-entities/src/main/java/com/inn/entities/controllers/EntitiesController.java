package com.inn.entities.controllers;

import com.inn.entities.dtos.EntitiesDTO;
import com.inn.entities.entities.Entities;
import com.inn.entities.exceptions.ResourceNotFoundException;
import com.inn.entities.services.EntitiesService;

import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/entities")
public class EntitiesController {

    @Autowired
    private EntitiesService entitiesService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<EntitiesDTO> getAllEntities() {
        return entitiesService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntitiesDTO> getEntityById(@PathVariable Long id) {
        Entities entity = entitiesService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :: " + id));
        return ResponseEntity.ok(convertToDTO(entity));
    }

    @PostMapping
    public EntitiesDTO createEntity(@Valid @RequestBody EntitiesDTO entitiesDTO) {
        Entities entity = convertToEntity(entitiesDTO);
        return convertToDTO(entitiesService.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntitiesDTO> updateEntity(@PathVariable Long id, @Valid @RequestBody EntitiesDTO entitiesDTO) {
        Entities entity = entitiesService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :: " + id));
        modelMapper.map(entitiesDTO, entity);
        return ResponseEntity.ok(convertToDTO(entitiesService.save(entity)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable Long id) {
        entitiesService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :: " + id));
        entitiesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private EntitiesDTO convertToDTO(Entities entity) {
        return modelMapper.map(entity, EntitiesDTO.class);
    }

    private Entities convertToEntity(EntitiesDTO entitiesDTO) {
        return modelMapper.map(entitiesDTO, Entities.class);
    }
}