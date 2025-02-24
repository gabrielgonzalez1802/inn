package com.inn.orders.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TypeTaxeDTO {

    private Long typeTaxeId;

    @NotBlank(message = "Name Taxe is mandatory")
    private String nameTaxe;
}