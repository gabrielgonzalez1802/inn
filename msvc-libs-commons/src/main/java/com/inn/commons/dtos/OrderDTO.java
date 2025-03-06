package com.inn.commons.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderDTO {
	
    private Long orderId;

    @NotNull
    private Long entityId;
    
    private Long lotId;
    
    private Long sunagroGuideId;
    
    private String deliveryNote;

    private String observations;
    
    private String responsibleUser;

    @NotNull
    private LocalDateTime orderDate;
    
    private LocalDateTime registrationDatetime;
    
    @NotNull
    private Long warehouseId;

    @NotNull
    private Long orderStatusId;
    
    private OrderStatusTypeDTO orderStatusType;
}