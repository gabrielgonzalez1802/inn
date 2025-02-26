package com.inn.entities.controllers;

import com.inn.entities.config.RequiresRoles;
import com.inn.entities.dtos.SumagroGuidesDTO;
import com.inn.entities.entities.SumagroGuides;
import com.inn.entities.exceptions.ResourceNotFoundException;
import com.inn.entities.services.SumagroGuidesService;

import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/entities/sumagro-guides")
public class SumagroGuidesController {

    @Autowired
    private SumagroGuidesService sumagroGuidesService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public List<SumagroGuidesDTO> getAllSumagroGuides() {
        return sumagroGuidesService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<SumagroGuidesDTO> getSumagroGuideById(@PathVariable Long id) {
        SumagroGuides sumagroGuide = sumagroGuidesService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sumagro guide not found for this id :: " + id));
        return ResponseEntity.ok(convertToDTO(sumagroGuide));
    }

    @PostMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public SumagroGuidesDTO createSumagroGuide(@Valid @RequestBody SumagroGuidesDTO sumagroGuidesDTO) {
        SumagroGuides sumagroGuide = convertToEntity(sumagroGuidesDTO);
        return convertToDTO(sumagroGuidesService.save(sumagroGuide));
    }

    @PutMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<SumagroGuidesDTO> updateSumagroGuide(@PathVariable Long id, @Valid @RequestBody SumagroGuidesDTO sumagroGuidesDTO) {
        SumagroGuides sumagroGuide = sumagroGuidesService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sumagro guide not found for this id :: " + id));
        modelMapper.map(sumagroGuidesDTO, sumagroGuide);
        return ResponseEntity.ok(convertToDTO(sumagroGuidesService.save(sumagroGuide)));
    }

    @DeleteMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteSumagroGuide(@PathVariable Long id) {
        sumagroGuidesService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sumagro guide not found for this id :: " + id));
        sumagroGuidesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private SumagroGuidesDTO convertToDTO(SumagroGuides sumagroGuide) {
        return modelMapper.map(sumagroGuide, SumagroGuidesDTO.class);
    }

    private SumagroGuides convertToEntity(SumagroGuidesDTO sumagroGuidesDTO) {
        return modelMapper.map(sumagroGuidesDTO, SumagroGuides.class);
    }
}