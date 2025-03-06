package com.inn.products.dtos;

import java.util.List;

import lombok.Data;

@Data
public class ProductList {
	private Long orderId;
    private List<Product> products;
    private Integer total_products;

    @Data
    public static class Product {
        private Long id;
        private Integer cantidad;
        private Long warehouseId;
        private Long Movement_type_id;
    }
}