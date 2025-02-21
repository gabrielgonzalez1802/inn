package com.inn.address.controllers;

import com.inn.address.dtos.AddressDTO;
import com.inn.address.entities.Address;
import com.inn.address.exceptions.ResourceNotFoundException;
import com.inn.address.services.AddressService;

import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<AddressDTO> getAllAddresses() {
        return addressService.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id) {
        Address address = addressService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + id));
        return ResponseEntity.ok(convertToDTO(address));
    }

    @PostMapping
    public AddressDTO createAddress(@Valid @RequestBody AddressDTO addressDTO) {
        Address address = convertToEntity(addressDTO);
        return convertToDTO(addressService.save(address));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @Valid @RequestBody AddressDTO addressDTO) {
        Address address = addressService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + id));
        modelMapper.map(addressDTO, address);
        return ResponseEntity.ok(convertToDTO(addressService.save(address)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + id));
        addressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private AddressDTO convertToDTO(Address address) {
        return modelMapper.map(address, AddressDTO.class);
    }

    private Address convertToEntity(AddressDTO addressDTO) {
        return modelMapper.map(addressDTO, Address.class);
    }
}