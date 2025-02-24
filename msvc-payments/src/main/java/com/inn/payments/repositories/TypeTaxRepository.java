package com.inn.payments.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inn.payments.entities.TypeTax;

@Repository
public interface TypeTaxRepository extends JpaRepository<TypeTax, Long> {
}