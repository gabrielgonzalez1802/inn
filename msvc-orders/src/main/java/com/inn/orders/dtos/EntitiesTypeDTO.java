package com.inn.orders.dtos;

import lombok.Data;

import jakarta.validation.constraints.*;

@Data
public class EntitiesTypeDTO {

    private Long entityTypeId;

    @NotBlank
    @Size(max = 50)
    private String entityTypeName;
}