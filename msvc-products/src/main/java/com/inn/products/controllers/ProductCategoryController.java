package com.inn.products.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inn.commons.exceptions.ResourceNotFoundException;
import com.inn.products.config.RequiresRoles;
import com.inn.products.dtos.ProductCategoryDTO;
import com.inn.products.entities.ProductCategory;
import com.inn.products.services.ProductCategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products/categories")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public List<ProductCategoryDTO> getAllProductCategories() {
        return productCategoryService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<ProductCategoryDTO> getProductCategoryById(@PathVariable Long id) {
        ProductCategory productCategory = productCategoryService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Category not found for this id :: " + id));
        return ResponseEntity.ok(convertToDTO(productCategory));
    }

    @PostMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public ProductCategoryDTO createProductCategory(@Valid @RequestBody ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = convertToEntity(productCategoryDTO);
        return convertToDTO(productCategoryService.save(productCategory));
    }

    @PutMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<ProductCategoryDTO> updateProductCategory(@PathVariable Long id, @Valid @RequestBody ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = productCategoryService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Category not found for this id :: " + id));
        modelMapper.map(productCategoryDTO, productCategory);
        return ResponseEntity.ok(convertToDTO(productCategoryService.save(productCategory)));
    }

    @DeleteMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteProductCategory(@PathVariable Long id) {
        productCategoryService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Category not found for this id :: " + id));
        productCategoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ProductCategoryDTO convertToDTO(ProductCategory productCategory) {
        return modelMapper.map(productCategory, ProductCategoryDTO.class);
    }

    private ProductCategory convertToEntity(ProductCategoryDTO productCategoryDTO) {
        return modelMapper.map(productCategoryDTO, ProductCategory.class);
    }
}