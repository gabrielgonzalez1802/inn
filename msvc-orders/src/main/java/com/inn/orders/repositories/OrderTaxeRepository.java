package com.inn.orders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inn.orders.entities.OrderTaxe;

@Repository
public interface OrderTaxeRepository extends JpaRepository<OrderTaxe, Long> {
}