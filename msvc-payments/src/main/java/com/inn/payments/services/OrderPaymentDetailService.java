package com.inn.payments.services;

import java.util.List;

import com.inn.payments.dtos.OrderPaymentDetailDto;

public interface OrderPaymentDetailService {
    List<OrderPaymentDetailDto> findAll();
    OrderPaymentDetailDto findById(Long id);
    OrderPaymentDetailDto save(OrderPaymentDetailDto orderPaymentDetailDto);
    void deleteById(Long id);
}