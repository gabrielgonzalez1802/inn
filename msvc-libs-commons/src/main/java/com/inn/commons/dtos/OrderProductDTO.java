package com.inn.commons.dtos;

import com.inn.commons.dtos.ProductDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductDTO {

    private Long ordProdId;

    @NotNull(message = "Order ID is mandatory")
    private Long orderId;

    @NotNull(message = "Product ID is mandatory")
    private Long productId;
    
    @NotNull(message = "Quantity is mandatory")
    @PositiveOrZero(message = "Quantity must be positive or zero")
    private Integer quantity;
    
    private ProductDTO product;
}