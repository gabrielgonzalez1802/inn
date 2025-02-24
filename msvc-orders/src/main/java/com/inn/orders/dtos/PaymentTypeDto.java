package com.inn.orders.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentTypeDto {

    private Long paymentTypeId;

    @NotBlank(message = "El tipo de pago no puede estar vacio")
    @NotNull(message = "El tipo de pago no puede ser nulo")
    private String paymentTypeName;
}