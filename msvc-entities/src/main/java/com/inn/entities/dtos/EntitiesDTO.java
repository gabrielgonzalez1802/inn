package com.inn.entities.dtos;

import lombok.Data;

import jakarta.validation.constraints.*;

@Data
public class EntitiesDTO {

    private Long entityId;

    @NotNull
    private Long entityTypeId;

    @NotBlank
    @Size(max = 50)
    private String entityName;

    @NotBlank
    @Size(max = 50)
    private String entityLastName;

    @NotBlank
    @Email
    @Size(max = 100)
    private String entityEmail;

    @NotBlank
    @Size(min = 8, max = 100)
    private String entityPassword;

    @NotBlank
    @Size(max = 15)
    private String entityPhone;
}