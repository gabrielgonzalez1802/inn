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

import com.inn.payments.dtos.OrderPaymentDetailDto;
import com.inn.payments.services.OrderPaymentDetailService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payments/order-details")
public class OrderPaymentDetailController {

    @Autowired
    private OrderPaymentDetailService orderPaymentDetailService;

    @GetMapping
    public ResponseEntity<List<OrderPaymentDetailDto>> findAll() {
        List<OrderPaymentDetailDto> orderPaymentDetails = orderPaymentDetailService.findAll();
        return ResponseEntity.ok(orderPaymentDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderPaymentDetailDto> findById(@PathVariable Long id) {
        OrderPaymentDetailDto orderPaymentDetailDto = orderPaymentDetailService.findById(id);
        return ResponseEntity.ok(orderPaymentDetailDto);
    }

    @PostMapping
    public ResponseEntity<OrderPaymentDetailDto> save(@Valid @RequestBody OrderPaymentDetailDto orderPaymentDetailDto) {
        OrderPaymentDetailDto savedOrderPaymentDetail = orderPaymentDetailService.save(orderPaymentDetailDto);
        return ResponseEntity.ok(savedOrderPaymentDetail);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        orderPaymentDetailService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}