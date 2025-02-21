package com.inn.entities.exceptions;

public class ResourcesFoundException extends RuntimeException {
    public ResourcesFoundException(String message) {
        super(message);
    }

    public ResourcesFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}