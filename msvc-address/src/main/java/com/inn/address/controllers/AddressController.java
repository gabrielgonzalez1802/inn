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

import com.inn.address.config.RequiresRoles;
import com.inn.address.dtos.AddressDTO;
import com.inn.address.entities.Address;
import com.inn.address.exceptions.ResourceNotFoundException;
import com.inn.address.services.AddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public List<AddressDTO> getAllAddresses() {
        return addressService.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id) {
        Address address = addressService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + id));
        return ResponseEntity.ok(convertToDTO(address));
    }

    @PostMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public AddressDTO createAddress(@Valid @RequestBody AddressDTO addressDTO) {
        Address address = convertToEntity(addressDTO);
        return convertToDTO(addressService.save(address));
    }

    @PutMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @Valid @RequestBody AddressDTO addressDTO) {
        Address address = addressService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + id));
        modelMapper.map(addressDTO, address);
        return ResponseEntity.ok(convertToDTO(addressService.save(address)));
    }

    @DeleteMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
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