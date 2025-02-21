package com.inn.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inn.products.entities.OrderProduct;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}