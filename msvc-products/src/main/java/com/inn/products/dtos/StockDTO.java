package com.inn.products.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StockDTO {

    private Long stockId;

    @NotNull(message = "Product ID is mandatory")
    private Long productId;

    @NotNull(message = "Quantity is mandatory")
    private Long quantity;

    @NotNull(message = "Warehouse ID is mandatory")
    private Long warehouseId;
}