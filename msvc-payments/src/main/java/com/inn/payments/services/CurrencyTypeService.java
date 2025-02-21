package com.inn.payments.services;

import java.util.List;

import com.inn.payments.dtos.CurrencyTypeDto;

public interface CurrencyTypeService {
    List<CurrencyTypeDto> findAll();
    CurrencyTypeDto findById(Long id);
    CurrencyTypeDto save(CurrencyTypeDto currencyTypeDto);
    void deleteById(Long id);
}