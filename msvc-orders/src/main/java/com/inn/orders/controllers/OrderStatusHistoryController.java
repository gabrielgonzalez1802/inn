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

import com.inn.orders.config.RequiresRoles;
import com.inn.orders.dtos.OrderStatusHistoryDTO;
import com.inn.orders.entities.OrderStatusHistory;
import com.inn.orders.exceptions.ResourceNotFoundException;
import com.inn.orders.services.OrderStatusHistoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders/status-history")
public class OrderStatusHistoryController {

    @Autowired
    private OrderStatusHistoryService orderStatusHistoryService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public List<OrderStatusHistoryDTO> getAllOrderStatusHistories() {
        return orderStatusHistoryService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<OrderStatusHistoryDTO> getOrderStatusHistoryById(@PathVariable Long id) {
        OrderStatusHistory orderStatusHistory = orderStatusHistoryService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderStatusHistory not found for this id :: " + id));
        return ResponseEntity.ok(convertToDTO(orderStatusHistory));
    }

    @PostMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public OrderStatusHistoryDTO createOrderStatusHistory(@Valid @RequestBody OrderStatusHistoryDTO orderStatusHistoryDTO) {
        OrderStatusHistory orderStatusHistory = convertToEntity(orderStatusHistoryDTO);
        return convertToDTO(orderStatusHistoryService.save(orderStatusHistory));
    }

    @PutMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<OrderStatusHistoryDTO> updateOrderStatusHistory(@PathVariable Long id, @Valid @RequestBody OrderStatusHistoryDTO orderStatusHistoryDTO) {
        OrderStatusHistory orderStatusHistory = orderStatusHistoryService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderStatusHistory not found for this id :: " + id));
        modelMapper.map(orderStatusHistoryDTO, orderStatusHistory);
        return ResponseEntity.ok(convertToDTO(orderStatusHistoryService.save(orderStatusHistory)));
    }

    @DeleteMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteOrderStatusHistory(@PathVariable Long id) {
        orderStatusHistoryService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderStatusHistory not found for this id :: " + id));
        orderStatusHistoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private OrderStatusHistoryDTO convertToDTO(OrderStatusHistory orderStatusHistory) {
        return modelMapper.map(orderStatusHistory, OrderStatusHistoryDTO.class);
    }

    private OrderStatusHistory convertToEntity(OrderStatusHistoryDTO orderStatusHistoryDTO) {
        return modelMapper.map(orderStatusHistoryDTO, OrderStatusHistory.class);
    }
}