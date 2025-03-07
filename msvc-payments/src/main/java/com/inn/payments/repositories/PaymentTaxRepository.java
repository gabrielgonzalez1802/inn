package com.inn.payments.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inn.payments.entities.PaymentTax;

@Repository
public interface PaymentTaxRepository extends JpaRepository<PaymentTax, Long> {
}