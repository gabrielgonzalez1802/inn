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

import com.inn.payments.dtos.PaymentTypeDto;
import com.inn.payments.services.PaymentTypeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payments/types")
public class PymentTypeController {

    @Autowired
    private PaymentTypeService paymentTypeService;

    @GetMapping
    public ResponseEntity<List<PaymentTypeDto>> findAll() {
        List<PaymentTypeDto> paymentTypees = paymentTypeService.findAll();
        return ResponseEntity.ok(paymentTypees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentTypeDto> findById(@PathVariable Long id) {
        PaymentTypeDto paymentTypeDto = paymentTypeService.findById(id);
        return ResponseEntity.ok(paymentTypeDto);
    }

    @PostMapping
    public ResponseEntity<PaymentTypeDto> save(@Valid @RequestBody PaymentTypeDto paymentTypeDto) {
        PaymentTypeDto savedPaymentType = paymentTypeService.save(paymentTypeDto);
        return ResponseEntity.ok(savedPaymentType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        paymentTypeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}