package com.inn.products.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class MovementDTO {

    private Long movementId;

    @NotNull(message = "Warehouse ID is mandatory")
    private Long warehouseId;

    @NotNull(message = "Product ID is mandatory")
    private Long productId;

    @NotNull(message = "Lot ID is mandatory")
    private Long lotId;

    private Date movementDate;

    @NotBlank(message = "Action description is mandatory")
    private String actionDescription;

    @NotNull(message = "Quantity is mandatory")
    private Long quantity;

    @NotNull(message = "Entity ID is mandatory")
    private Long entityId;

    @NotNull(message = "Sunagro guide ID is mandatory")
    private Long sunagroGuideId;

    private String deliveryNote;
    private String purchaseOrder;
    private String observations;
    private String responsibleUser;
    private Date registrationDatetime;
}