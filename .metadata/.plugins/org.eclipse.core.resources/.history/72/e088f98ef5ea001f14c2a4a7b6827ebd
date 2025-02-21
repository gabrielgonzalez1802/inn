package com.inn.orders.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDTO {

    private Long productId;

    @NotBlank(message = "Product name is mandatory")
    private String productName;

    @NotNull(message = "Product price is mandatory")
    private Long productPrice;

    @NotNull(message = "Product category ID is mandatory")
    private Long prodCategoryId;

    @NotNull(message = "Presentation type ID is mandatory")
    private Long presentationTypeId;

    @NotBlank(message = "Product code is mandatory")
    private String productCode;

    private String productDescription;

    @NotNull(message = "Product unit measure ID is mandatory")
    private Long productUnitMessureId;
}