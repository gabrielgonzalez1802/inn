package com.inn.products.dtos;

public enum TipoMovimiento {
    DEPOSITO(1),
    RETIRO(2),
    TRANSFERENCIA(3),
    PAGO_SERVICIO(4),
    COMPRA(5);

    private final int valor;

    TipoMovimiento(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static TipoMovimiento fromValor(int valor) {
        for (TipoMovimiento tipo : values()) {
            if (tipo.getValor() == valor) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Valor no válido para TipoMovimiento: " + valor);
    }
}