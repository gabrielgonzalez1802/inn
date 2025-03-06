package com.inn.products.dtos;

import com.inn.commons.dtos.ProductDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StockDTO {
	
    @NotNull(message = "Product ID is mandatory")
    private Long productId;
    
    @NotNull(message = "Warehouse ID is mandatory")
    private Long warehouseId;

    @NotNull(message = "Quantity is mandatory")
    private Long quantity;

    private ProductDTO product;
}