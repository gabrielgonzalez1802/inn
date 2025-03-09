package com.inn.orders.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.inn.commons.dtos.MovementDTO;
import com.inn.commons.dtos.OrderProductDTO;

import jakarta.validation.Valid;

@FeignClient(name = "msvc-products")
public interface ProductsClientRest {

	@GetMapping("/api/products/order-products/orders/{orderId}")
    public List<OrderProductDTO> getAllOrderProductByOrderId(@PathVariable Long orderId);
	
	@PostMapping("/api/products/order-products")
	public OrderProductDTO createOrderProduct(@Valid @RequestBody OrderProductDTO orderProductDTO);
	
	@PostMapping("/api/products/movements")
	public MovementDTO createMovement(@Valid @RequestBody MovementDTO movementDTO);
}