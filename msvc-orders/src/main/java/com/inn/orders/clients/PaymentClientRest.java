package com.inn.orders.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.inn.orders.dtos.OrderPaymentDetailDto;

import jakarta.validation.Valid;

@FeignClient(name = "msvc-payments")
public interface PaymentClientRest {

    @GetMapping("/api/payments/order-details/order/{orderId}")
    public OrderPaymentDetailDto findByOrderId(@PathVariable Long orderId);
    
    @PostMapping("/api/payments/order-details")
    public OrderPaymentDetailDto save(@Valid @RequestBody OrderPaymentDetailDto orderPaymentDetailDto);
}