package com.inn.entities.services;

import com.inn.entities.entities.EntitiesType;
import com.inn.entities.repositories.EntitiesTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntitiesTypeService {

    @Autowired
    private EntitiesTypeRepository entitiesTypeRepository;

    public List<EntitiesType> findAll() {
        return entitiesTypeRepository.findAll();
    }

    public Optional<EntitiesType> findById(Long id) {
        return entitiesTypeRepository.findById(id);
    }

    public EntitiesType save(EntitiesType entitiesType) {
        return entitiesTypeRepository.save(entitiesType);
    }

    public void deleteById(Long id) {
        entitiesTypeRepository.deleteById(id);
    }
}