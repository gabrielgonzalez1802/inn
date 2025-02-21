package com.inn.entities.services;

import com.inn.entities.entities.Entities;
import com.inn.entities.repositories.EntitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntitiesService {

    @Autowired
    private EntitiesRepository entitiesRepository;

    public List<Entities> findAll() {
        return entitiesRepository.findAll();
    }

    public Optional<Entities> findById(Long id) {
        return entitiesRepository.findById(id);
    }

    public Entities save(Entities entities) {
        return entitiesRepository.save(entities);
    }

    public void deleteById(Long id) {
        entitiesRepository.deleteById(id);
    }
}