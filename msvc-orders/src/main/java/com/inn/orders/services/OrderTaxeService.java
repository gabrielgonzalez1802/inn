package com.inn.orders.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.orders.entities.OrderTaxe;
import com.inn.orders.repositories.OrderTaxeRepository;

@Service
public class OrderTaxeService {

    @Autowired
    private OrderTaxeRepository orderTaxeRepository;

    public List<OrderTaxe> findAll() {
        return orderTaxeRepository.findAll();
    }

    public Optional<OrderTaxe> findById(Long id) {
        return orderTaxeRepository.findById(id);
    }

    public OrderTaxe save(OrderTaxe order) {
        return orderTaxeRepository.save(order);
    }

    public void deleteById(Long id) {
        orderTaxeRepository.deleteById(id);
    }
}