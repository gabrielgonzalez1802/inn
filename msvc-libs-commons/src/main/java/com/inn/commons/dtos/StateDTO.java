package com.inn.commons.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StateDTO {

    private Long stateId;

    @NotBlank(message = "State name cannot be blank")
    @Size(max = 255, message = "State name cannot exceed 255 characters")
    private String stateName;
}