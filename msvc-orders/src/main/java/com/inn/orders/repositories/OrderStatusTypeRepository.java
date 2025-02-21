package com.inn.orders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inn.orders.entities.OrderStatusType;

@Repository
public interface OrderStatusTypeRepository extends JpaRepository<OrderStatusType, Long> {
}