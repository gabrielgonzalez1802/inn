package com.inn.gateway.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.resource.NoResourceFoundException;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import lombok.Data;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<CustomResponse> badRequestHandler(BadRequestException bre) {
		LOGGER.error("badRequestHandler - message {}", bre.getMessage());
		CustomResponse cr = new CustomResponse((HttpStatus.BAD_REQUEST), bre.getMessage());
		return new ResponseEntity<>(cr, cr.getStatus());
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<CustomResponse> notFoundExceptionHandler(NotFoundException nfe) {
		LOGGER.error("notFoundExceptionHandler - message: {}", nfe.getMessage());
		CustomResponse cr = new CustomResponse((HttpStatus.NOT_FOUND), nfe.getMessage());
		return new ResponseEntity<>(cr, cr.getStatus());
	}
	
	@ExceptionHandler(ResourcesNotFoundException.class)
	public ResponseEntity<CustomResponse> resourcesNotFoundExceptionHandler(ResourcesNotFoundException nfe) {
		LOGGER.warn("resourcesNotFoundExceptionHandler - message: {}", nfe.getMessage());
		CustomResponse cr = new CustomResponse((HttpStatus.NOT_FOUND), nfe.getMessage());
		return new ResponseEntity<>(cr, cr.getStatus());
	}
	
	@ExceptionHandler(ResourcesFoundException.class)
	public ResponseEntity<CustomResponse> resourcesFoundExceptionHandler(ResourcesFoundException nfe) {
		LOGGER.warn("resourcesFoundExceptionHandler - message: {}", nfe.getMessage());
		CustomResponse cr = new CustomResponse((HttpStatus.IM_USED), nfe.getMessage());
		return new ResponseEntity<>(cr, cr.getStatus());
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<CustomResponse> integrityConstraintViolationExceptionHandler(SQLIntegrityConstraintViolationException nfe) {
		LOGGER.warn("integrityConstraintViolationExceptionHandler - message: {}", nfe.getMessage());
		CustomResponse cr = new CustomResponse((HttpStatus.BAD_REQUEST), nfe.getMessage());
		return new ResponseEntity<>(cr, cr.getStatus());
	}

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<CustomResponse> noResourceFoundExceptionExceptionHandler(NoResourceFoundException nfe) {
		LOGGER.warn("noResourceFoundExceptionExceptionHandler - message: {}", nfe.getMessage());
		CustomResponse cr = new CustomResponse((HttpStatus.NOT_FOUND), nfe.getMessage());
		return new ResponseEntity<>(cr, cr.getStatus());
	}

    @ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<CustomResponse> resourceNotFoundException(ResourceNotFoundException ex) {
		LOGGER.warn("noResourceFoundExceptionExceptionHandler - message: {}", ex.getMessage());
		CustomResponse cr = new CustomResponse((HttpStatus.NOT_FOUND), ex.getMessage());
		return new ResponseEntity<>(cr, HttpStatus.NOT_FOUND);
	}
    
    @ExceptionHandler(RoleAuthorizationException.class)
	public ResponseEntity<CustomResponse> roleAuthorizationException(RoleAuthorizationException ex) {
		LOGGER.warn("roleAuthorizationException - message: {}", ex.getMessage());
		CustomResponse cr = new CustomResponse((HttpStatus.UNAUTHORIZED), ex.getMessage());
		return new ResponseEntity<>(cr, HttpStatus.UNAUTHORIZED);
	}
    
    @ExceptionHandler(JwtValidationException.class)
	public ResponseEntity<CustomResponse> jwtValidationException(JwtValidationException ex) {
		LOGGER.warn("jwtValidationException - message: {}", ex.getMessage());
		CustomResponse cr = new CustomResponse((HttpStatus.UNAUTHORIZED), ex.getMessage());
		return new ResponseEntity<>(cr, HttpStatus.UNAUTHORIZED);
	}
    
    @ExceptionHandler(Exception.class)
	public ResponseEntity<CustomResponse> exception(Exception ex) {
		LOGGER.warn("exception - message: {}", ex.getMessage());
		CustomResponse cr = new CustomResponse((HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage());
		return new ResponseEntity<>(cr, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Data
	public static class CustomResponse {

		private final LocalDateTime date;

		private final HttpStatus status;

		private final String message;

		public CustomResponse(HttpStatus status, String message) {
			this.date = LocalDateTime.now();
			this.status = status;
			this.message = message;
		}
	}
}