package com.inn.orders.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inn.orders.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByEntityId(Long entityId);
}