package com.inn.products.dtos;

import lombok.Data;

import jakarta.validation.constraints.*;

@Data
public class WarehouseDTO {
    @NotNull(message = "El ID del almacén no puede ser nulo")
    private Long warehouseId;

    @NotBlank(message = "El nombre del almacén no puede estar vacío")
    @Size(max = 100, message = "El nombre del almacén no puede tener más de 100 caracteres")
    private String warehouseName;

    @NotBlank(message = "La dirección del almacén no puede estar vacía")
    @Size(max = 255, message = "La dirección del almacén no puede tener más de 255 caracteres")
    private String warehouseAddress;

    @NotBlank(message = "El teléfono del almacén no puede estar vacío")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "El teléfono del almacén no es válido")
    private String warehousePhone;

    @NotBlank(message = "El contacto del almacén no puede estar vacío")
    @Size(max = 100, message = "El contacto del almacén no puede tener más de 100 caracteres")
    private String warehouseContact;
}