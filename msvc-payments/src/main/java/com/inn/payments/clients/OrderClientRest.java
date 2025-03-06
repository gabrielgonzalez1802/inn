package com.inn.payments.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.inn.commons.dtos.OrderDTO;

@FeignClient(name = "msvc-orders")
public interface OrderClientRest {

	@GetMapping("/api/orders/{id}")
    public OrderDTO getOrderById(@PathVariable Long id);
	
    @PutMapping("/api/orders/{id}/status/{orderStatusId}")
	public String updateOrderStatus(@PathVariable Long id, @PathVariable Long orderStatusId);
}