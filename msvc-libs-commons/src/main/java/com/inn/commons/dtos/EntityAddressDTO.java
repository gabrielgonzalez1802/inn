package com.inn.commons.dtos;

import lombok.Data;

@Data
public class EntityAddressDTO {
	
    private Long id;
    private Long entityId;
    private Long addressId;
    
    private AddressDTO address;
}