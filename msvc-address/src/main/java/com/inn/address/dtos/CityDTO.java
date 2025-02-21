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
public class CityDTO {

    private Long cityId;

    @NotBlank(message = "City name cannot be blank")
    @Size(max = 255, message = "City name cannot exceed 255 characters")
    private String cityName;

    @NotNull(message = "State ID cannot be null")
    private Long stateId;
}