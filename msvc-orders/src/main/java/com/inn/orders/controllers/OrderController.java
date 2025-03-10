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

import com.inn.commons.dtos.OrderDTO;
import com.inn.commons.exceptions.ResourceNotFoundException;
import com.inn.orders.config.RequiresRoles;
import com.inn.orders.dtos.EnrichedOrderDTO;
import com.inn.orders.dtos.OrderEnrichedDTO;
import com.inn.orders.entities.Order;
import com.inn.orders.services.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService ordersService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public List<OrderDTO> getAllOrder() {
        return ordersService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/entities/{entityId}")
    @RequiresRoles({"ROLE_ADMIN"})
    public List<OrderDTO> getAllOrderByEntityId(@PathVariable Long entityId) {
        return ordersService.findAllByEntityId(entityId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        Order order = ordersService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + id));
        return ResponseEntity.ok(convertToDTO(order));
    }
    
    @GetMapping("/{id}/enriched")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<OrderEnrichedDTO> getOrderEnrichedById(@PathVariable Long id) {
        OrderEnrichedDTO orderEnrichedDTO = ordersService.findOrderEnrichedById(id);
        
        if(orderEnrichedDTO==null) {
        	throw new ResourceNotFoundException("Order not found for this id :: " + id);
        }
        
        return ResponseEntity.ok(orderEnrichedDTO);
    }
    
    @PostMapping("/enriched")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<OrderDTO> createOrderEnriched(@Valid @RequestBody EnrichedOrderDTO enrichedOrderDTO) {
        return ResponseEntity.ok(convertToDTO(ordersService.saveEnrichedOrder(enrichedOrderDTO)));
    }

    @PostMapping
    @RequiresRoles({"ROLE_ADMIN"})
    public OrderDTO createOrder(@Valid @RequestBody OrderDTO ordersDTO) {
        Order order = convertToEntity(ordersDTO);
        return convertToDTO(ordersService.save(order));
    }

    @PutMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @Valid @RequestBody OrderDTO ordersDTO) {
        Order order = ordersService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + id));
        ordersDTO.setOrderId(id);
        modelMapper.map(ordersDTO, order);
        return ResponseEntity.ok(convertToDTO(ordersService.save(order)));
    }
    
    @PutMapping("/{id}/status/{orderStatusId}")
    @RequiresRoles({"ROLE_ADMIN"})
	public ResponseEntity<String> updateOrderStatus(@PathVariable Long id, @PathVariable Long orderStatusId) {
    	ordersService.updateOrderStatus(id, orderStatusId);
		return ResponseEntity.ok("Order status updated successfully");
	}

    @DeleteMapping("/{id}")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        ordersService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + id));
        ordersService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private OrderDTO convertToDTO(Order order) {
        return modelMapper.map(order, OrderDTO.class);
    }

    private Order convertToEntity(OrderDTO ordersDTO) {
        return modelMapper.map(ordersDTO, Order.class);
    }
}