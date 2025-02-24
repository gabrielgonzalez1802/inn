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

import com.inn.payments.dtos.TypeTaxDto;
import com.inn.payments.services.TypeTaxService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payments/taxes/types")
public class TypeTaxController {

    @Autowired
    private TypeTaxService typeTaxService;

    @GetMapping
    public ResponseEntity<List<TypeTaxDto>> findAll() {
        List<TypeTaxDto> typeTaxes = typeTaxService.findAll();
        return ResponseEntity.ok(typeTaxes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeTaxDto> findById(@PathVariable Long id) {
        TypeTaxDto typeTaxDto = typeTaxService.findById(id);
        return ResponseEntity.ok(typeTaxDto);
    }

    @PostMapping
    public ResponseEntity<TypeTaxDto> save(@Valid @RequestBody TypeTaxDto typeTaxDto) {
        TypeTaxDto savedTypeTax = typeTaxService.save(typeTaxDto);
        return ResponseEntity.ok(savedTypeTax);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        typeTaxService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}