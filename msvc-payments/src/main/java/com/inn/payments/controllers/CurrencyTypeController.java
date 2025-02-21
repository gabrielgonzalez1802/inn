package com.inn.payments.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inn.payments.dtos.CurrencyTypeDto;
import com.inn.payments.services.CurrencyTypeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payments/currency-types")
public class CurrencyTypeController {

    @Autowired
    private CurrencyTypeService currencyTypeService;

    @GetMapping
    public ResponseEntity<List<CurrencyTypeDto>> findAll() {
        List<CurrencyTypeDto> currencyTypes = currencyTypeService.findAll();
        return ResponseEntity.ok(currencyTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyTypeDto> findById(@PathVariable Long id) {
        CurrencyTypeDto currencyTypeDto = currencyTypeService.findById(id);
        return ResponseEntity.ok(currencyTypeDto);
    }

    @PostMapping
    public ResponseEntity<CurrencyTypeDto> save(@Valid @RequestBody CurrencyTypeDto currencyTypeDto) {
        CurrencyTypeDto savedCurrencyType = currencyTypeService.save(currencyTypeDto);
        return ResponseEntity.ok(savedCurrencyType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        currencyTypeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}