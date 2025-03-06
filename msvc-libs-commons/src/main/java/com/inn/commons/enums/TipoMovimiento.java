package com.inn.commons.enums;

public enum TipoMovimiento {
    DEPOSITO(1, "RECEPCION"),
    RETIRO(2, "DESPACHO"),
    TRANSFERENCIA(3, "MERMA"),
    PAGO_SERVICIO(4, "INV_INICIAL"),
    COMPRA(5, "COMPRA");

    private final int valor;
    private final String nombre;

    TipoMovimiento(int valor, String nombre) {
        this.valor = valor;
        this.nombre = nombre;
    }

    public int getValor() {
        return valor;
    }

    public String getNombre() {
        return nombre;
    }

    public static TipoMovimiento fromValor(int valor) {
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