package com.inn.products.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductCategoryDTO {

    private Long prodCategoryId;

    @NotBlank(message = "Category name is mandatory")
    private String categoryName;
}