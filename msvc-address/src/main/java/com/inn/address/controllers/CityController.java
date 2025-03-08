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

import com.inn.address.entities.City;
import com.inn.address.services.CityService;
import com.inn.commons.config.RequiresRoles;
import com.inn.commons.dtos.CityDTO;
import com.inn.commons.exceptions.ResourceNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/addresses/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<CityDTO> getAllCities() {
        return cityService.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    
    @GetMapping("/states/{stateId}")
    @RequiresRoles({"ROLE_ADMIN"})
    public List<CityDTO> getAllCitiesByStateId(Long stateId) {
        return cityService.findAllByStateId(stateId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<CityDTO> getCityById(@PathVariable Long id) {
        City city = cityService.findById(id).orElseThrow(() -> new ResourceNotFoundException("City not found for this id :: " + id));
        return ResponseEntity.ok(convertToDTO(city));
    }

    @PostMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public CityDTO createCity(@Valid @RequestBody CityDTO cityDTO) {
        City city = convertToEntity(cityDTO);
        return convertToDTO(cityService.save(city));
    }

    @PutMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<CityDTO> updateCity(@PathVariable Long id, @Valid @RequestBody CityDTO cityDTO) {
        City city = cityService.findById(id).orElseThrow(() -> new ResourceNotFoundException("City not found for this id :: " + id));
        modelMapper.map(cityDTO, city);
        return ResponseEntity.ok(convertToDTO(cityService.save(city)));
    }

    @DeleteMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        cityService.findById(id).orElseThrow(() -> new ResourceNotFoundException("City not found for this id :: " + id));
        cityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private CityDTO convertToDTO(City city) {
        return modelMapper.map(city, CityDTO.class);
    }

    private City convertToEntity(CityDTO cityDTO) {
        return modelMapper.map(cityDTO, City.class);
    }
}