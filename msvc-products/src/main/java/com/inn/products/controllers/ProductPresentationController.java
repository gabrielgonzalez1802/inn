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

import com.inn.products.config.RequiresRoles;
import com.inn.products.dtos.ProductPresentationDTO;
import com.inn.products.entities.ProductPresentation;
import com.inn.products.exceptions.ResourceNotFoundException;
import com.inn.products.services.ProductPresentationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products/presentations")
public class ProductPresentationController {

    @Autowired
    private ProductPresentationService productPresentationService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public List<ProductPresentationDTO> getAllProductPresentation() {
        return productPresentationService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<ProductPresentationDTO> getProductPresentationById(@PathVariable Long id) {
        ProductPresentation productPresentation = productPresentationService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Presentation not found for this id :: " + id));
        return ResponseEntity.ok(convertToDTO(productPresentation));
    }

    @PostMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public ProductPresentationDTO createProductPresentation(@Valid @RequestBody ProductPresentationDTO productPresentationDTO) {
        ProductPresentation productPresentation = convertToEntity(productPresentationDTO);
        return convertToDTO(productPresentationService.save(productPresentation));
    }

    @PutMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<ProductPresentationDTO> updateProductPresentation(@PathVariable Long id, @Valid @RequestBody ProductPresentationDTO productPresentationDTO) {
        ProductPresentation productPresentation = productPresentationService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Presentation not found for this id :: " + id));
        modelMapper.map(productPresentationDTO, productPresentation);
        return ResponseEntity.ok(convertToDTO(productPresentationService.save(productPresentation)));
    }

    @DeleteMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteProductPresentation(@PathVariable Long id) {
        productPresentationService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Presentation not found for this id :: " + id));
        productPresentationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ProductPresentationDTO convertToDTO(ProductPresentation productPresentation) {
        return modelMapper.map(productPresentation, ProductPresentationDTO.class);
    }

    private ProductPresentation convertToEntity(ProductPresentationDTO productPresentationDTO) {
        return modelMapper.map(productPresentationDTO, ProductPresentation.class);
    }
}