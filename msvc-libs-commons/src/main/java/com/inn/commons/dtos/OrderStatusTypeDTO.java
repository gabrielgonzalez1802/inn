package com.inn.commons.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderStatusTypeDTO {
    private Long orderStatusTypeId;

    @NotNull
    private String orderStatusName;
}