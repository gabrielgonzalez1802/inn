package com.inn.clients.controllers;

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

import com.inn.clients.dtos.ClientDTO;
import com.inn.clients.entities.Client;
import com.inn.clients.exceptions.ResourceNotFoundException;
import com.inn.clients.services.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<ClientDTO> getAllClients() {
        return clientService.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        Client client = clientService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + id));
        return ResponseEntity.ok(convertToDTO(client));
    }

    @PostMapping
    public ClientDTO createClient(@Valid @RequestBody ClientDTO clientDTO) {
        Client client = convertToEntity(clientDTO);
        return convertToDTO(clientService.save(client));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDTO) {
        Client client = clientService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + id));
        modelMapper.map(clientDTO, client);
        return ResponseEntity.ok(convertToDTO(clientService.save(client)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + id));
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ClientDTO convertToDTO(Client client) {
        return modelMapper.map(client, ClientDTO.class);
    }

    private Client convertToEntity(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, Client.class);
    }
}