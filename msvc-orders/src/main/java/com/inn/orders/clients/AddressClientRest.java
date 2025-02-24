package com.inn.orders.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.inn.orders.dtos.EntityAddressDTO;

@FeignClient(name = "msvc-address", url = "${address.service.url}")
public interface AddressClientRest {

	@GetMapping("/api/addresses/entities-adrress/entities/{entityId}")
	List<EntityAddressDTO> getAllByEntityId(@PathVariable Long entityId);
}