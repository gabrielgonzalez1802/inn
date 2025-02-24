package com.inn.orders.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.inn.orders.dtos.OrderProductDTO;

@FeignClient(name = "msvc-products", url = "${products.service.url}")
public interface ProductsClientRest {

	@GetMapping("/api/products/order-products/orders/{orderId}")
    public List<OrderProductDTO> getAllOrderProductByOrderId(@PathVariable Long orderId);
}