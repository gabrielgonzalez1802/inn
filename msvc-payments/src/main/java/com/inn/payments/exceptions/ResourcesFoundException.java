package com.inn.payments.exceptions;

public class ResourcesFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public ResourcesFoundException(String message) {
        super(message);
    }

    public ResourcesFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}