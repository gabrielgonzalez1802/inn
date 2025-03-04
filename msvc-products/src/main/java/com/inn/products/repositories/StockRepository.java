package com.inn.products.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inn.products.entities.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
	List<Stock> findByWarehouseId(Long warehouseId);
}