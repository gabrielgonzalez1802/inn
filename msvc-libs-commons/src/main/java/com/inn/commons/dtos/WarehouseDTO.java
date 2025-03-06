package com.inn.commons.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class WarehouseDTO {
	
    private Long warehouseId;

    @NotBlank(message = "El nombre del almacén no puede estar vacío")
    @Size(max = 100, message = "El nombre del almacén no puede tener más de 100 caracteres")
    private String warehouseName;

    private Long addressId;
    
    @NotBlank(message = "El teléfono del almacén no puede estar vacío")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "El teléfono del almacén no es válido")
    private String warehousePhone;

    @NotBlank(message = "El contacto del almacén no puede estar vacío")
    @Size(max = 100, message = "El contacto del almacén no puede tener más de 100 caracteres")
    private String warehouseContact;
    
    private AddressDTO address;
}