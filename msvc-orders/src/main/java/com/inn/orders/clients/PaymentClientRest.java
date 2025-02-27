package com.inn.orders.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.inn.orders.dtos.OrderPaymentDetailDto;

@FeignClient(name = "msvc-payments")
public interface PaymentClientRest {

    @GetMapping("/api/payments/order-details/order/{orderId}")
    public OrderPaymentDetailDto findByOrderId(@PathVariable Long orderId);
}