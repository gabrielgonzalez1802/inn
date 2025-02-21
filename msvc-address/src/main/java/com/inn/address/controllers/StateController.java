package com.inn.address.controllers;

import com.inn.address.dtos.StateDTO;
import com.inn.address.entities.State;
import com.inn.address.exceptions.ResourceNotFoundException;
import com.inn.address.services.StateService;

import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<StateDTO> getStateById(@PathVariable Long id) {
        State state = stateService.findById(id).orElseThrow(() -> new ResourceNotFoundException("State not found for this id :: " + id));
        return ResponseEntity.ok(convertToDTO(state));
    }

    @PostMapping
    public StateDTO createState(@Valid @RequestBody StateDTO stateDTO) {
        State state = convertToEntity(stateDTO);
        return convertToDTO(stateService.save(state));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StateDTO> updateState(@PathVariable Long id, @Valid @RequestBody StateDTO stateDTO) {
        State state = stateService.findById(id).orElseThrow(() -> new ResourceNotFoundException("State not found for this id :: " + id));
        modelMapper.map(stateDTO, state);
        return ResponseEntity.ok(convertToDTO(stateService.save(state)));
    }

    @DeleteMapping("/{id}")
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