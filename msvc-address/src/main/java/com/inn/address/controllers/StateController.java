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

import com.inn.address.entities.State;
import com.inn.address.services.StateService;
import com.inn.commons.config.RequiresRoles;
import com.inn.commons.dtos.StateDTO;
import com.inn.commons.exceptions.ResourceNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/addresses/states")
public class StateController {

    @Autowired
    private StateService stateService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<StateDTO> getAllStates() {
        return stateService.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<StateDTO> getStateById(@PathVariable Long id) {
        State state = stateService.findById(id).orElseThrow(() -> new ResourceNotFoundException("State not found for this id :: " + id));
        return ResponseEntity.ok(convertToDTO(state));
    }

    @PostMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public StateDTO createState(@Valid @RequestBody StateDTO stateDTO) {
        State state = convertToEntity(stateDTO);
        return convertToDTO(stateService.save(state));
    }

    @PutMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<StateDTO> updateState(@PathVariable Long id, @Valid @RequestBody StateDTO stateDTO) {
        State state = stateService.findById(id).orElseThrow(() -> new ResourceNotFoundException("State not found for this id :: " + id));
        modelMapper.map(stateDTO, state);
        return ResponseEntity.ok(convertToDTO(stateService.save(state)));
    }

    @DeleteMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteState(@PathVariable Long id) {
        stateService.findById(id).orElseThrow(() -> new ResourceNotFoundException("State not found for this id :: " + id));
        stateService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private StateDTO convertToDTO(State state) {
        return modelMapper.map(state, StateDTO.class);
    }

    private State convertToEntity(StateDTO stateDTO) {
        return modelMapper.map(stateDTO, State.class);
    }
}