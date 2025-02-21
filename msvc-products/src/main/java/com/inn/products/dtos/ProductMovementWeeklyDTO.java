package com.inn.products.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductMovementWeeklyDTO {
	private Long productId;
	private String productName;
	private Double totalRecepcion;
	private Double semana1;
	private Double semana2;
	private Double semana3;
	private Double semana4;
	private Double semana5;
}