package com.inn.commons.exceptions;

public class RoleAuthorizationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RoleAuthorizationException(String message) {
        super(message);
    }
}