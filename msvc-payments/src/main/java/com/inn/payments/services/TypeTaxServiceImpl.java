package com.inn.payments.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.payments.dtos.TypeTaxDto;
import com.inn.payments.entities.TypeTax;
import com.inn.payments.exceptions.ResourceNotFoundException;
import com.inn.payments.repositories.TypeTaxRepository;

@Service
public class TypeTaxServiceImpl implements TypeTaxService {

    @Autowired
    private TypeTaxRepository typeTaxRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TypeTaxDto> findAll() {
        return typeTaxRepository.findAll().stream()
                .map(typeTax -> modelMapper.map(typeTax, TypeTaxDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TypeTaxDto findById(Long id) {
        TypeTax typeTax = typeTaxRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Impuesto no encontrado con id: " + id));
        return modelMapper.map(typeTax, TypeTaxDto.class);
    }

    @Override
    public TypeTaxDto save(TypeTaxDto typeTaxDto) {
        TypeTax typeTax = modelMapper.map(typeTaxDto, TypeTax.class);
        typeTax = typeTaxRepository.save(typeTax);
        return modelMapper.map(typeTax, TypeTaxDto.class);
    }

    @Override
    public void deleteById(Long id) {
        if (!typeTaxRepository.existsById(id)) {
            throw new ResourceNotFoundException("Impuesto no encontrado con id: " + id);
        }
        typeTaxRepository.deleteById(id);
    }
}