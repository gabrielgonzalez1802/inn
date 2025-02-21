package com.inn.payments.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.payments.dtos.CurrencyTypeDto;
import com.inn.payments.entities.CurrencyType;
import com.inn.payments.exceptions.ResourceNotFoundException;
import com.inn.payments.repositories.CurrencyTypeRepository;

@Service
public class CurrencyTypeServiceImpl implements CurrencyTypeService {

    @Autowired
    private CurrencyTypeRepository currencyTypeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CurrencyTypeDto> findAll() {
        return currencyTypeRepository.findAll().stream()
                .map(currencyType -> modelMapper.map(currencyType, CurrencyTypeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CurrencyTypeDto findById(Long id) {
        CurrencyType currencyType = currencyTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de moneda no encontrado con id: " + id));
        return modelMapper.map(currencyType, CurrencyTypeDto.class);
    }

    @Override
    public CurrencyTypeDto save(CurrencyTypeDto currencyTypeDto) {
        CurrencyType currencyType = modelMapper.map(currencyTypeDto, CurrencyType.class);
        currencyType = currencyTypeRepository.save(currencyType);
        return modelMapper.map(currencyType, CurrencyTypeDto.class);
    }

    @Override
    public void deleteById(Long id) {
        if (!currencyTypeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tipo de moneda no encontrado con id: " + id);
        }
        currencyTypeRepository.deleteById(id);
    }
}