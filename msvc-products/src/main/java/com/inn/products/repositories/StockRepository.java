package com.inn.products.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inn.products.entities.Stock;
import com.inn.products.entities.StockId;

@Repository
public interface StockRepository extends JpaRepository<Stock, StockId> {
    List<Stock> findByIdWarehouseId(Long warehouseId);
}