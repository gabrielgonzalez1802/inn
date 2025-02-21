package com.inn.products.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.products.entities.ProductPresentation;
import com.inn.products.repositories.ProductPresentationRepository;

@Service
public class ProductPresentationService {

    @Autowired
    private ProductPresentationRepository ProductPresentationRepository;

    public List<ProductPresentation> findAll() {
        return ProductPresentationRepository.findAll();
    }

    public Optional<ProductPresentation> findById(Long id) {
        return ProductPresentationRepository.findById(id);
    }

    public ProductPresentation save(ProductPresentation ProductPresentation) {
        return ProductPresentationRepository.save(ProductPresentation);
    }

    public void deleteById(Long id) {
        ProductPresentationRepository.deleteById(id);
    }
}