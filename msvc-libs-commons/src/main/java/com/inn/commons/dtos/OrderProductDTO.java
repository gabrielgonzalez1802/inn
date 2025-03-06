package com.inn.commons.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderProductDTO {

    private Long ordProdId;

    @NotNull(message = "Order ID is mandatory")
    private Long orderId;

    @NotNull(message = "Product ID is mandatory")
    private Long productId;
    
    private ProductDTO product;
}