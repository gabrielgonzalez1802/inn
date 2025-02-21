package com.inn.orders.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderStatusHistoryDTO {
    private Long orderStatusHistoryId;

    @NotNull
    private Long systemUserId;

    @NotNull
    private Long typeOrderStatusId;

    @NotNull
    private Long orderId;
}