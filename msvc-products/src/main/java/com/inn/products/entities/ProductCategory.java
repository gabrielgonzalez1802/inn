package com.inn.products.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "product_category")
@Data
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_category_id")
    private Long prodCategoryId;
    @Column(name = "categoryName", nullable = false, unique = true)
    private String categoryName;
}