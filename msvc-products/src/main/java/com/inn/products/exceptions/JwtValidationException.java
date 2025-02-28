package com.inn.products.exceptions;

public class JwtValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public JwtValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtValidationException(String message) {
        super(message);
    }
}