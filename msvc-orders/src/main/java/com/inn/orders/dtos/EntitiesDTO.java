package com.inn.orders.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

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
    
    private EntitiesTypeDTO entityType;
}