package com.inn.address.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.address.entities.EntityAddress;
import com.inn.address.repositories.EntityAddressRepository;

@Service
public class EntityAddressService {

    @Autowired
    private EntityAddressRepository entityAddressRepository;

    public List<EntityAddress> findAll() {
        return entityAddressRepository.findAll();
    }
    
    public List<EntityAddress> findAllByEntityId(Long entityId) {
        return entityAddressRepository.findByEntityId(entityId);
    }

    public Optional<EntityAddress> findById(Long id) {
        return entityAddressRepository.findById(id);
    }

    public EntityAddress save(EntityAddress address) {
        return entityAddressRepository.save(address);
    }

    public void deleteById(Long id) {
    	entityAddressRepository.deleteById(id);
    }
}