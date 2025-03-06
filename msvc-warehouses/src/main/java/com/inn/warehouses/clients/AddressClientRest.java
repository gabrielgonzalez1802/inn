package com.inn.warehouses.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.inn.commons.dtos.AddressDTO;

@FeignClient(name = "msvc-address")
public interface AddressClientRest {

	@GetMapping("/api/addresses/{id}")
	public AddressDTO getAddressById(@PathVariable Long id);
}