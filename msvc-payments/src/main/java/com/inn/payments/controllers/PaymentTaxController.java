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

import com.inn.payments.dtos.PaymentTaxDto;
import com.inn.payments.services.PaymentTaxService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payments/taxes")
public class PaymentTaxController {

    @Autowired
    private PaymentTaxService paymentTaxService;

    @GetMapping
    public ResponseEntity<List<PaymentTaxDto>> findAll() {
        List<PaymentTaxDto> paymentTaxes = paymentTaxService.findAll();
        return ResponseEntity.ok(paymentTaxes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentTaxDto> findById(@PathVariable Long id) {
        PaymentTaxDto paymentTaxDto = paymentTaxService.findById(id);
        return ResponseEntity.ok(paymentTaxDto);
    }

    @PostMapping
    public ResponseEntity<PaymentTaxDto> save(@Valid @RequestBody PaymentTaxDto paymentTaxDto) {
        PaymentTaxDto savedPaymentTax = paymentTaxService.save(paymentTaxDto);
        return ResponseEntity.ok(savedPaymentTax);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        paymentTaxService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}