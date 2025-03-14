package com.inn.products.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.products.entities.UnitMeasure;
import com.inn.products.repositories.UnitMeasureRepository;

@Service
public class UnitMeasureService {

    @Autowired
    private UnitMeasureRepository unitMeasureRepository;

    public List<UnitMeasure> findAll() {
        return unitMeasureRepository.findAll();
    }

    public Optional<UnitMeasure> findById(Long id) {
        return unitMeasureRepository.findById(id);
    }

    public UnitMeasure save(UnitMeasure unitMeasure) {
        return unitMeasureRepository.save(unitMeasure);
    }

    public void deleteById(Long id) {
        unitMeasureRepository.deleteById(id);
    }
}