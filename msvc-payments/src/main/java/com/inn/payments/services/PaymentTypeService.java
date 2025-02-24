package com.inn.payments.services;

import java.util.List;

import com.inn.payments.dtos.PaymentTypeDto;

public interface PaymentTypeService {

	List<PaymentTypeDto> findAll();

	PaymentTypeDto findById(Long id);

	PaymentTypeDto save(PaymentTypeDto typeTaxDto);

	void deleteById(Long id);
}