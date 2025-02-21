package com.inn.payments.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.payments.dtos.PaymentTaxDto;
import com.inn.payments.entities.PaymentTax;
import com.inn.payments.exceptions.ResourceNotFoundException;
import com.inn.payments.repositories.PaymentTaxRepository;

@Service
public class PaymentTaxServiceImpl implements PaymentTaxService {

    @Autowired
    private PaymentTaxRepository paymentTaxRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PaymentTaxDto> findAll() {
        return paymentTaxRepository.findAll().stream()
                .map(paymentTax -> modelMapper.map(paymentTax, PaymentTaxDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PaymentTaxDto findById(Long id) {
        PaymentTax paymentTax = paymentTaxRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Impuesto no encontrado con id: " + id));
        return modelMapper.map(paymentTax, PaymentTaxDto.class);
    }

    @Override
    public PaymentTaxDto save(PaymentTaxDto paymentTaxDto) {
        PaymentTax paymentTax = modelMapper.map(paymentTaxDto, PaymentTax.class);
        paymentTax = paymentTaxRepository.save(paymentTax);
        return modelMapper.map(paymentTax, PaymentTaxDto.class);
    }

    @Override
    public void deleteById(Long id) {
        if (!paymentTaxRepository.existsById(id)) {
            throw new ResourceNotFoundException("Impuesto no encontrado con id: " + id);
        }
        paymentTaxRepository.deleteById(id);
    }
}