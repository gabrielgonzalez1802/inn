package com.inn.commons.enums;

public enum TipoMovimiento {
	RECEPCION(1L, "RECEPCION"),
	DESPACHO(2L, "DESPACHO"),
	MERMA(3L, "MERMA"),
	INV_INICIAL(4L, "INV_INICIAL");

    private final Long valor;
    private final String nombre;

    TipoMovimiento(Long valor, String nombre) {
        this.valor = valor;
        this.nombre = nombre;
    }

    public Long getValor() {
        return valor;
    }

    public String getNombre() {
        return nombre;
    }

    public static TipoMovimiento fromValor(Long valor) {
        for (TipoMovimiento tipo : values()) {
            if (tipo.getValor() == valor) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Valor no válido para TipoMovimiento: " + valor);
    }

    public static TipoMovimiento fromNombre(String nombre) {
        for (TipoMovimiento tipo : values()) {
            if (tipo.getNombre().equalsIgnoreCase(nombre)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Nombre no válido para TipoMovimiento: " + nombre);
    }
}