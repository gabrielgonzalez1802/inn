package com.inn.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inn.products.entities.ProductCategory;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}