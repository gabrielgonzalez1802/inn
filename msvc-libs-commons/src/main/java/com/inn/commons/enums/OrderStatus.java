package com.inn.commons.enums;

public enum OrderStatus {
	POR_APROBAR(1L, "POR APROBAR"),
    VALIDANDO_PAGO(2L, "VALIDANDO PAGO"),
    EN_PROCESO(3L, "EN PROCESO"),
    APROBADA(4L, "APROBADA"),
    RECHAZADA(5L, "RECHAZADA"),
    CERRADA(6L, "CERRADA"),
    PENDIENTE(6L, "PENDIENTE");

    private final long valor;
    private final String nombre;

    OrderStatus(long valor, String nombre) {
        this.valor = valor;
        this.nombre = nombre;
    }

    public long getValor() {
        return valor;
    }

    public String getNombre() {
        return nombre;
    }

    public static OrderStatus fromValor(long valor) {
        for (OrderStatus orderStatus : values()) {
            if (orderStatus.getValor() == valor) {
                return orderStatus;
            }
        }
        throw new IllegalArgumentException("Valor no válido para OrderStatus: " + valor);
    }

    public static OrderStatus fromNombre(String nombre) {
        for (OrderStatus orderStatus : values()) {
            if (orderStatus.getNombre().equalsIgnoreCase(nombre)) {
                return orderStatus;
            }
        }
        throw new IllegalArgumentException("Nombre no válido para OrderStatus: " + nombre);
    }
}