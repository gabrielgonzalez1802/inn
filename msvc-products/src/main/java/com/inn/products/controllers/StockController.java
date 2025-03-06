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

import com.inn.commons.exceptions.ResourceNotFoundException;
import com.inn.products.config.RequiresRoles;
import com.inn.products.dtos.StockDTO;
import com.inn.products.entities.Stock;
import com.inn.products.entities.StockId;
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
    
    @GetMapping("/warehouses/{warehouseId}")
    @RequiresRoles({"ROLE_ADMIN"})
    public List<StockDTO> getAllStocksByWarehouseId(@PathVariable Long warehouseId) {
        return stockService.findAllByWarehouseId(warehouseId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

	@GetMapping("/{productId}/{warehouseId}")
	@RequiresRoles({ "ROLE_ADMIN" })
	public ResponseEntity<StockDTO> getStockById(@Valid @RequestBody StockId stockId) {
		Stock stock = stockService.findById(stockId).orElseThrow(() -> new ResourceNotFoundException(
				"Stock not found for this productId and warehouseId :: " + stockId.getProductId() + ", " + stockId.getWarehouseId()));
		return ResponseEntity.ok(convertToDTO(stock));
	}
	
    @PostMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public StockDTO createStock(@Valid @RequestBody StockDTO stockDTO) {
        Stock stock = convertToEntity(stockDTO);
        
		try {
			Stock stockEntity = stockService.save(stock);
			
        	return convertToDTO(stockEntity);
		} catch (Exception e) {
            throw new RuntimeException("Could not create stock - cause :: " + e.getMessage());
	    }
    }

    @PutMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<StockDTO> updateStock(@Valid @RequestBody StockDTO stockDTO) {
    	
        Stock stock = stockService.findById(new StockId(stockDTO.getProductId(), stockDTO.getWarehouseId()))
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found for this productId and warehouseId :: " + stockDTO.getProductId() + ", " + stockDTO.getWarehouseId()));

        stock.setQuantity(stockDTO.getQuantity());

        return ResponseEntity.ok(convertToDTO(stockService.save(stock)));
    }

    @DeleteMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteStock(@Valid @RequestBody StockId stockId) {
        Stock stock = stockService.findById(stockId)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found for this productId and warehouseId :: " + stockId.getProductId() + ", " + stockId.getWarehouseId()));
        stockService.deleteById(stock.getId());
        return ResponseEntity.noContent().build();
    }

    private StockDTO convertToDTO(Stock stock) {
    	StockDTO stockDTO = modelMapper.map(stock, StockDTO.class);
    	stockDTO.setProductId(stock.getId().getProductId());
    	stockDTO.setWarehouseId(stock.getId().getWarehouseId());
        return stockDTO;
    }

    private Stock convertToEntity(StockDTO stockDTO) {
    	Stock stockEnity = modelMapper.map(stockDTO, Stock.class);
    	stockEnity.setId(new StockId(stockDTO.getProductId(), stockDTO.getWarehouseId()));
        return stockEnity;
    }
}