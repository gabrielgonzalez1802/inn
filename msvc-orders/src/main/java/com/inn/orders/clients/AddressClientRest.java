package com.inn.orders.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.inn.commons.dtos.EntityAddressDTO;

@FeignClient(name = "msvc-address")
public interface AddressClientRest {

	@GetMapping("/api/addresses/entities-adrress/entities/{entityId}")
	List<EntityAddressDTO> getAllByEntityId(@PathVariable Long entityId);
}
