package com.inn.payments.dtos;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderPaymentDetailDto {
	
    private Long paymentDetailId;

    @NotNull(message = "El precio total de la orden no puede ser nulo")
    private BigDecimal orderFullPrice;

    @NotNull(message = "El identificador de la orden no puede ser nulo")
    private Long orderId;

    @NotNull(message = "El identificador de la moneda no puede ser nulo")
    private Long currencyId;
    
    @Column(name = "payment_type_id")
    private Long paymentTypeId;

    private CurrencyTypeDto currency;
    
    private PaymentTypeDto paymentType;
}