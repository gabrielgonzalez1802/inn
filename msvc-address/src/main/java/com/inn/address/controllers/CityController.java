package com.inn.address.controllers;

import com.inn.address.dtos.CityDTO;
import com.inn.address.entities.City;
import com.inn.address.exceptions.ResourceNotFoundException;
import com.inn.address.services.CityService;

import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> getCityById(@PathVariable Long id) {
        City city = cityService.findById(id).orElseThrow(() -> new ResourceNotFoundException("City not found for this id :: " + id));
        return ResponseEntity.ok(convertToDTO(city));
    }

    @PostMapping
    public CityDTO createCity(@Valid @RequestBody CityDTO cityDTO) {
        City city = convertToEntity(cityDTO);
        return convertToDTO(cityService.save(city));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDTO> updateCity(@PathVariable Long id, @Valid @RequestBody CityDTO cityDTO) {
        City city = cityService.findById(id).orElseThrow(() -> new ResourceNotFoundException("City not found for this id :: " + id));
        modelMapper.map(cityDTO, city);
        return ResponseEntity.ok(convertToDTO(cityService.save(city)));
    }

    @DeleteMapping("/{id}")
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