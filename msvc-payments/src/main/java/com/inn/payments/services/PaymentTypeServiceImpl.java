package com.inn.payments.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.commons.exceptions.ResourceNotFoundException;
import com.inn.payments.dtos.PaymentTypeDto;
import com.inn.payments.entities.PaymentType;
import com.inn.payments.repositories.PaymentTypeRepository;

@Service
public class PaymentTypeServiceImpl implements PaymentTypeService {

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PaymentTypeDto> findAll() {
        return paymentTypeRepository.findAll().stream()
                .map(paymentType -> modelMapper.map(paymentType, PaymentTypeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PaymentTypeDto findById(Long id) {
        PaymentType paymentType = paymentTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de pago no encontrado con id: " + id));
        return modelMapper.map(paymentType, PaymentTypeDto.class);
    }

    @Override
    public PaymentTypeDto save(PaymentTypeDto paymentTypeDto) {
        PaymentType paymentType = modelMapper.map(paymentTypeDto, PaymentType.class);
        paymentType = paymentTypeRepository.save(paymentType);
        return modelMapper.map(paymentType, PaymentTypeDto.class);
    }

    @Override
    public void deleteById(Long id) {
        if (!paymentTypeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tipo de pago no encontrado con id: " + id);
        }
        paymentTypeRepository.deleteById(id);
    }
}