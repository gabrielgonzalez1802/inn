package com.inn.orders.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderNotCreatedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String nombreRecurso;
	private List<Long> idProductos;
	private long id;

	public OrderNotCreatedException(List<Long> idProductos) {
		super(String.format("La orden no fue persistida ya que los productos: %s No se encuentran registrados en DB", listaAStringComas(idProductos)));
	}
	
	public static String listaAStringComas(List<Long> listaIds) {
        if (listaIds == null || listaIds.isEmpty()) {
            return "";
        }

        return listaIds.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }
}