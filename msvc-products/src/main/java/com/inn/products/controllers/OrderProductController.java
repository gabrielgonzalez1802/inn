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

import com.inn.commons.dtos.OrderProductDTO;
import com.inn.commons.exceptions.ResourceNotFoundException;
import com.inn.products.config.RequiresRoles;
import com.inn.products.entities.OrderProduct;
import com.inn.products.services.OrderProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products/order-products")
public class OrderProductController {

    @Autowired
    private OrderProductService orderProductService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public List<OrderProductDTO> getAllOrderProduct() {
        return orderProductService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/orders/{orderId}")
    @RequiresRoles({"ROLE_ADMIN"})
    public List<OrderProductDTO> getAllOrderProductByOrderId(@PathVariable Long orderId) {
        return orderProductService.findAllByOrderId(orderId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<OrderProductDTO> getOrderProductById(@PathVariable Long id) {
        OrderProduct orderProduct = orderProductService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order Product not found for this id :: " + id));
        return ResponseEntity.ok(convertToDTO(orderProduct));
    }

    @PostMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public OrderProductDTO createOrderProduct(@Valid @RequestBody OrderProductDTO orderProductDTO) {
        OrderProduct orderProduct = convertToEntity(orderProductDTO);
        return convertToDTO(orderProductService.save(orderProduct));
    }

    @PutMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<OrderProductDTO> updateOrderProduct(@PathVariable Long id, @Valid @RequestBody OrderProductDTO orderProductDTO) {
        OrderProduct orderProduct = orderProductService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order Product not found for this id :: " + id));
        modelMapper.map(orderProductDTO, orderProduct);
        return ResponseEntity.ok(convertToDTO(orderProductService.save(orderProduct)));
    }

    @DeleteMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteOrderProduct(@PathVariable Long id) {
        orderProductService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order Product not found for this id :: " + id));
        orderProductService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private OrderProductDTO convertToDTO(OrderProduct orderProduct) {
        return modelMapper.map(orderProduct, OrderProductDTO.class);
    }

    private OrderProduct convertToEntity(OrderProductDTO orderProductDTO) {
        return modelMapper.map(orderProductDTO, OrderProduct.class);
    }
}