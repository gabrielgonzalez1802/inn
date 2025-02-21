package com.inn.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inn.products.entities.UnitMeasure;

@Repository
public interface UnitMeasureRepository extends JpaRepository<UnitMeasure, Long> {
}