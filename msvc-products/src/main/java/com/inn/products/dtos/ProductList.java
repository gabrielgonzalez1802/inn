package com.inn.products.dtos;

import java.util.List;

import lombok.Data;

@Data
public class ProductList {
    private List<Product> productos;
    private int total_products;

    @Data
    public static class Product {
        private int id;
        private int cantidad;
        private int warehouseId;
        private int Movement_type_id;
    }
}