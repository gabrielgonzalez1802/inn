package com.inn.payments.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inn.payments.entities.CurrencyType;

@Repository
public interface CurrencyTypeRepository extends JpaRepository<CurrencyType, Long> {
}