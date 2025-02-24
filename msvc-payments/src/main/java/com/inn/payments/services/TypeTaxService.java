package com.inn.payments.services;

import java.util.List;

import com.inn.payments.dtos.TypeTaxDto;

public interface TypeTaxService {

	List<TypeTaxDto> findAll();

	TypeTaxDto findById(Long id);

	TypeTaxDto save(TypeTaxDto typeTaxDto);

	void deleteById(Long id);
}