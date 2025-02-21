package com.inn.products.exceptions;

public class ResourcesNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public ResourcesNotFoundException(String message) {
        super(message);
    }

    public ResourcesNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}