package com.inn.orders.dtos;

import com.inn.commons.dtos.ProductDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderProductDTO {

    private Long ordProdId;

    @NotNull(message = "Order ID is mandatory")
    private Long orderId;

    @NotNull(message = "Product ID is mandatory")
    private Long productId;
    
    private ProductDTO product;

	public OrderProductDTO(Long orderId, Long productId) {
		super();
		this.orderId = orderId;
		this.productId = productId;
	}
}