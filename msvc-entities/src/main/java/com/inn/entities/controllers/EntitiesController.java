package com.inn.entities.controllers;

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

import com.inn.entities.config.RequiresRoles;
import com.inn.entities.dtos.EntitiesDTO;
import com.inn.entities.entities.Entities;
import com.inn.entities.exceptions.ResourceNotFoundException;
import com.inn.entities.services.EntitiesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/entities")
public class EntitiesController {

    @Autowired
    private EntitiesService entitiesService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public List<EntitiesDTO> getAllEntities() {
        return entitiesService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<EntitiesDTO> getEntityById(@PathVariable Long id) {
        Entities entities = entitiesService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :: " + id));
        return ResponseEntity.ok(convertToDTO(entities));
    }

    @PostMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public EntitiesDTO createEntity(@Valid @RequestBody EntitiesDTO entitiesDTO) {
        Entities entities = convertToEntity(entitiesDTO);
        return convertToDTO(entitiesService.save(entities));
    }

    @PutMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<EntitiesDTO> updateEntity(@PathVariable Long id, @Valid @RequestBody EntitiesDTO entitiesDTO) {
        Entities entities = entitiesService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :: " + id));
        modelMapper.map(entitiesDTO, entities);
        return ResponseEntity.ok(convertToDTO(entitiesService.save(entities)));
    }

    @DeleteMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteEntity(@PathVariable Long id) {
        entitiesService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :: " + id));
        entitiesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private EntitiesDTO convertToDTO(Entities entities) {
        return modelMapper.map(entities, EntitiesDTO.class);
    }

    private Entities convertToEntity(EntitiesDTO entitiesDTO) {
        return modelMapper.map(entitiesDTO, Entities.class);
    }
}