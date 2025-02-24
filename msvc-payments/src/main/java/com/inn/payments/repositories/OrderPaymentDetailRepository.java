package com.inn.payments.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inn.payments.entities.OrderPaymentDetail;

@Repository
public interface OrderPaymentDetailRepository extends JpaRepository<OrderPaymentDetail, Long> {
	OrderPaymentDetail findByOrderId(Long orderId);
}