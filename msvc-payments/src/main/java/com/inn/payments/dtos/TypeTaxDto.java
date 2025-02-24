package com.inn.payments.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class TypeTaxDto {
	
    private Long typeTaxeId;
    
    @NotNull(message = "El nombre del tax no puede ser nulo")
    private String taxName;
    
    @PositiveOrZero(message = "No se aceptan numeros negativos")
    private Integer taxPercentaje;
}