package com.inn.orders.controllers;

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

import com.inn.orders.dtos.OrderTaxeDTO;
import com.inn.orders.entities.OrderTaxe;
import com.inn.orders.exceptions.ResourceNotFoundException;
import com.inn.orders.services.OrderTaxeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orderTaxes/taxes")
public class OrderTaxeController {

    @Autowired
    private OrderTaxeService orderTaxesService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<OrderTaxeDTO> getAllOrderTaxe() {
        return orderTaxesService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderTaxeDTO> getOrderTaxeById(@PathVariable Long id) {
        OrderTaxe orderTaxe = orderTaxesService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderTaxe not found for this id :: " + id));
        return ResponseEntity.ok(convertToDTO(orderTaxe));
    }

    @PostMapping
    public OrderTaxeDTO createOrderTaxe(@Valid @RequestBody OrderTaxeDTO orderTaxesDTO) {
        OrderTaxe orderTaxe = convertToEntity(orderTaxesDTO);
        return convertToDTO(orderTaxesService.save(orderTaxe));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderTaxeDTO> updateOrderTaxe(@PathVariable Long id, @Valid @RequestBody OrderTaxeDTO orderTaxesDTO) {
        OrderTaxe orderTaxe = orderTaxesService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderTaxe not found for this id :: " + id));
        modelMapper.map(orderTaxesDTO, orderTaxe);
        return ResponseEntity.ok(convertToDTO(orderTaxesService.save(orderTaxe)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderTaxe(@PathVariable Long id) {
        orderTaxesService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderTaxe not found for this id :: " + id));
        orderTaxesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private OrderTaxeDTO convertToDTO(OrderTaxe orderTaxe) {
        return modelMapper.map(orderTaxe, OrderTaxeDTO.class);
    }

    private OrderTaxe convertToEntity(OrderTaxeDTO orderTaxesDTO) {
        return modelMapper.map(orderTaxesDTO, OrderTaxe.class);
    }
}