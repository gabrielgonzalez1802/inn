package com.inn.payments.services;

import java.util.List;

import com.inn.payments.dtos.PaymentTaxDto;

public interface PaymentTaxService {
    List<PaymentTaxDto> findAll();
    PaymentTaxDto findById(Long id);
    PaymentTaxDto save(PaymentTaxDto paymentTaxDto);
    void deleteById(Long id);
}