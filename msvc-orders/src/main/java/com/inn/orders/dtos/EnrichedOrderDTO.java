package com.inn.orders.dtos;

import java.util.List;

import com.inn.commons.dtos.OrderDTO;
import com.inn.commons.dtos.ProductDTO;

import lombok.Data;

@Data
public class EnrichedOrderDTO {

	OrderDTO order;
	List<ProductDTO> products;
}