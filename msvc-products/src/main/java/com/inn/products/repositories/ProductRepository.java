package com.inn.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inn.products.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}