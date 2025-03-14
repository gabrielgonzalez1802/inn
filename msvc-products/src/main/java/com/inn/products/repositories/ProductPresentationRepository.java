package com.inn.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inn.products.entities.ProductPresentation;

@Repository
public interface ProductPresentationRepository extends JpaRepository<ProductPresentation, Long> {
}