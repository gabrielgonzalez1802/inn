package com.inn.products.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inn.products.config.RequiresRoles;
import com.inn.products.dtos.StockDTO;
import com.inn.products.entities.Stock;
import com.inn.products.exceptions.ResourceNotFoundException;
import com.inn.products.services.StockService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public List<StockDTO> getAllStocks() {
        return stockService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<StockDTO> getStockById(@PathVariable Long id) {
        Stock stock = stockService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found for this id :: " + id));
        return ResponseEntity.ok(convertToDTO(stock));
    }

    @PostMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public StockDTO createStock(@Valid @RequestBody StockDTO stockDTO) {
        Stock stock = convertToEntity(stockDTO);
        return convertToDTO(stockService.save(stock));
    }

    @PutMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<StockDTO> updateStock(@PathVariable Long id, @Valid @RequestBody StockDTO stockDTO) {
        Stock stock = stockService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found for this id :: " + id));
        modelMapper.map(stockDTO, stock);
        return ResponseEntity.ok(convertToDTO(stockService.save(stock)));
    }

    @DeleteMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found for this id :: " + id));
        stockService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private StockDTO convertToDTO(Stock stock) {
        return modelMapper.map(stock, StockDTO.class);
    }

    private Stock convertToEntity(StockDTO stockDTO) {
        return modelMapper.map(stockDTO, Stock.class);
    }
}