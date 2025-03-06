package com.inn.address.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.address.entities.City;
import com.inn.address.repositories.CityRepository;
import com.inn.commons.exceptions.ResourceNotFoundException;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    public City save(City city) {
        return cityRepository.save(city);
    }

    public void deleteById(Long id) {
        City city = cityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("City not found for this id :: " + id));
        cityRepository.delete(city);
    }
}