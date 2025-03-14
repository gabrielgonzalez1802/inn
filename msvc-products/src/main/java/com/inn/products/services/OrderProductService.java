package com.inn.products.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.products.entities.OrderProduct;
import com.inn.products.repositories.OrderProductRepository;

@Service
public class OrderProductService {

    @Autowired
    private OrderProductRepository orderProductRepository;

    public List<OrderProduct> findAll() {
        return orderProductRepository.findAll();
    }
    
    public List<OrderProduct> findAllByOrderId(Long idOrder) {
        return orderProductRepository.findByOrderId(idOrder);
    }

    public Optional<OrderProduct> findById(Long id) {
        return orderProductRepository.findById(id);
    }

    public OrderProduct save(OrderProduct orderProduct) {
        return orderProductRepository.save(orderProduct);
    }

    public void deleteById(Long id) {
        orderProductRepository.deleteById(id);
    }
}