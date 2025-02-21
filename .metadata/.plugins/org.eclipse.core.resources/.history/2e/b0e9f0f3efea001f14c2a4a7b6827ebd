package com.inn.products.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UnitMeasureDTO {

    private Long unitMeasureId;

    @NotBlank(message = "Unit measure name is mandatory")
    private String unitMeasureName;

    @NotBlank(message = "Unit measure abbreviation is mandatory")
    private String unitMeasureAbbreviation;
}