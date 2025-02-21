package com.inn.products.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class LotDTO {

    private Long lotId;

    @NotNull(message = "Product ID is mandatory")
    private Long productId;

    @NotBlank(message = "Lot number is mandatory")
    private String lotNumber;

    private Date manufactureDate;
    private Date expirationDate;

    @NotNull(message = "Initial quantity is mandatory")
    private Long initialQuantity;
}