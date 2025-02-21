package com.inn.entities.exceptions;

public class ResourcesNotFoundException extends RuntimeException {
    public ResourcesNotFoundException(String message) {
        super(message);
    }

    public ResourcesNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}