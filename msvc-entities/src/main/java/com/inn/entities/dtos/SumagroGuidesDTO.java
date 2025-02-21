package com.inn.entities.dtos;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;

@Data
public class SumagroGuidesDTO {

    private Long sumagroGuidesId;

    @NotBlank
    @Size(max = 20)
    private String guideNumber;

    @NotNull
    private Date emissionDate;

    @NotNull
    private Long entityId;
}