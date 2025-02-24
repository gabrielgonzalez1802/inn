package com.inn.orders.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.inn.orders.dtos.EntitiesDTO;

@FeignClient(name = "msvc-entities", url = "http://msvc-entities:8088")
public interface EntityClientRest {

	@GetMapping("/api/entities/{id}")
	public EntitiesDTO getEntityById(@PathVariable Long id);
}
