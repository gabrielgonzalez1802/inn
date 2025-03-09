package com.inn.orders.dtos;

import java.util.List;

import com.inn.commons.dtos.OrderDTO;
import com.inn.commons.dtos.ProductSimpleDTO;

import lombok.Data;

@Data
public class EnrichedOrderDTO {

	OrderDTO order;
	List<ProductSimpleDTO> products;
}