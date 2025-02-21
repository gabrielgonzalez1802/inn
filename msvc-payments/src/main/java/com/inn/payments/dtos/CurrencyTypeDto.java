package com.inn.payments.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CurrencyTypeDto {
    private Long currencyId;

    @NotBlank(message = "El nombre de la moneda no puede estar en blanco")
    private String currencyName;
}