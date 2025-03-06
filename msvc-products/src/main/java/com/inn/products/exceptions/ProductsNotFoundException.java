package com.inn.products.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductsNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private List<Long> idProductos;

	public ProductsNotFoundException(List<Long> idProductos) {
		super(String.format("los siguientes id de productos no se encontraron o no tienen suficiente stock en el almacen: %s", listaAStringComas(idProductos)));
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