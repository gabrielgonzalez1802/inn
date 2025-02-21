package com.inn.clients.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.clients.entities.Client;
import com.inn.clients.repositories.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
}