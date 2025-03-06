package com.inn.commons.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResourcesNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String nombreRecurso;
	private String nombreCampo;
	private long id;

	public ResourcesNotFoundException(String nombreRecurso, String nombreCampo, long id) {
		super(String.format("%s not found with: %s '%s' ", nombreRecurso, nombreCampo, id));
		this.nombreRecurso = nombreRecurso;
		this.nombreCampo = nombreCampo;
		this.id = id;
	}
}