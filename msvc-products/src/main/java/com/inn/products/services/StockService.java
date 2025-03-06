package com.inn.products.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.products.entities.Stock;
import com.inn.products.entities.StockId;
import com.inn.products.repositories.StockRepository;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public List<Stock> findAll() {
        return stockRepository.findAll();
    }
    
    public List<Stock> findAllByWarehouseId(Long warehouseId) {
        return stockRepository.findByIdWarehouseId(warehouseId);
    }

    public Optional<Stock> findById(StockId stockId) {
        return stockRepository.findById(stockId);
    }

    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    public void deleteById(StockId stockId) {
        stockRepository.deleteById(stockId);
    }
}