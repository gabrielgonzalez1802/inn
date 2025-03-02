package com.inn.products.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.inn.products.dtos.WarehouseDTO;

@FeignClient(name = "msvc-warehouses")
public interface WarehouseClientRest {

	@GetMapping("/api/warehouses/{id}")
	public WarehouseDTO getEntityById(@PathVariable Long id);
}
