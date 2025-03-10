package com.inn.products.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.products.entities.Lot;
import com.inn.products.repositories.LotRepository;

@Service
public class LotService {

    @Autowired
    private LotRepository lotRepository;

    public List<Lot> findAll() {
        return lotRepository.findAll();
    }

    public Optional<Lot> findById(Long id) {
        return lotRepository.findById(id);
    }

    public Lot save(Lot lots) {
        return lotRepository.save(lots);
    }

    public void deleteById(Long id) {
    	lotRepository.deleteById(id);
    }
}