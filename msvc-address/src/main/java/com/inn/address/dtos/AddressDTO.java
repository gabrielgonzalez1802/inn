package com.inn.address.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressDTO {

    private Long addressId;

    @NotNull(message = "Entity ID cannot be null")
    private Long entityId;

    @NotBlank(message = "Address cannot be blank")
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    private String address;

    @NotNull(message = "City ID cannot be null")
    private Long cityId;

    @NotNull(message = "State ID cannot be null")
    private Long stateId;

    @Size(max = 255, message = "Address comment cannot exceed 255 characters")
    private String addressComment;
    
    private CityDTO city;
}