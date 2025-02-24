package com.inn.products.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.inn.products.dtos.WarehouseDTO;

@FeignClient(name = "msvc-warehouses", url = "http://msvc-warehouses:8004")
public interface WarehouseClientRest {

	@GetMapping("/api/warehouse/{id}")
	public WarehouseDTO getEntityById(@PathVariable Long id);
}
