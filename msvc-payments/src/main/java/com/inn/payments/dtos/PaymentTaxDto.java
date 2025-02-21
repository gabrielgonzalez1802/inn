package com.inn.payments.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentTaxDto {
    private Long payTaxId;

    @NotNull(message = "El tipo de impuesto no puede ser nulo")
    private Long typeTaxesId;

    @NotNull(message = "El detalle de pago no puede ser nulo")
    private Long paymentDetailId;
}