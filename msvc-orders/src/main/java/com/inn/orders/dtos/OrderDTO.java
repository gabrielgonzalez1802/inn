package com.inn.orders.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderDTO {
	
    private Long orderId;

    @NotNull
    private Long clientId;

    @NotNull
    private LocalDateTime orderDate;

    @NotNull
    private Long orderStatusId;
    
    private OrderStatusTypeDTO orderStatusType;
}