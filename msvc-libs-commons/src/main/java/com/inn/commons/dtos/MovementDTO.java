package com.inn.commons.dtos;

import java.time.LocalDateTime;

import com.inn.commons.enums.TipoMovimiento;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class MovementDTO {
	
    private Long movementId;

    @NotNull(message = "Product ID is mandatory")
    private Long productId;
    
    @NotNull(message = "Warehouse ID is mandatory")
    private Long warehouseId;
    
    private LocalDateTime movementDate;
    
    private String actionDescription;
    
    @NotNull(message = "Quantity is mandatory")
    @PositiveOrZero(message = "The amount must be a positive number or zero")
    private Integer quantity;

    private TipoMovimiento movementType;
    
    private Long orderId;

    private ProductDTO product;
}