package com.inn.entities.services;

import com.inn.entities.entities.SumagroGuides;
import com.inn.entities.repositories.SumagroGuidesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SumagroGuidesService {

    @Autowired
    private SumagroGuidesRepository sumagroGuidesRepository;

    public List<SumagroGuides> findAll() {
        return sumagroGuidesRepository.findAll();
    }

    public Optional<SumagroGuides> findById(Long id) {
        return sumagroGuidesRepository.findById(id);
    }

    public SumagroGuides save(SumagroGuides sumagroGuides) {
        return sumagroGuidesRepository.save(sumagroGuides);
    }

    public void deleteById(Long id) {
        sumagroGuidesRepository.deleteById(id);
    }
}