package com.application.api.exception;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.JwtException;

@RestControllerAdvice
public class AuthenticationExceptionHandler {
	
	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<Map<Object, Object>> ResourceNotFoundExceptionHandler(ResourceNotFoundException exp) {
		Map<Object, Object> errorBody = Map.of("error", exp.getMessage());
		return new ResponseEntity<Map<Object,Object>>(errorBody, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = JwtException.class)
	public ResponseEntity<Map<Object, Object>> ResourceNotFoundExceptionHandler(JwtException exp) {
		Map<Object, Object> errorBody = Map.of("error", "invalid or expired jwt");
		return new ResponseEntity<Map<Object,Object>>(errorBody, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<Map<Object, Object>> jdbcConstraintViolationExceptionException(ConstraintViolationException exp) {
		Map<Object, Object> errorBody = Map.of("error", "data integrity violation");
		return new ResponseEntity<Map<Object,Object>>(errorBody, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = javax.validation.ConstraintViolationException.class)
	public ResponseEntity<Map<Object, Object>> constraintViolationException(javax.validation.ConstraintViolationException exp) {
		Map<Object, Object> errorBody = new LinkedHashMap<Object, Object>();
		errorBody.put("error", "data constraint violation");
		
		Map<String, String> constraintViolations = exp.getConstraintViolations().stream()
				.collect(Collectors.toMap(violation -> violation.getPropertyPath().toString(), violation -> violation.getMessage()));
		errorBody.put("violations", constraintViolations);
		return new ResponseEntity<Map<Object,Object>>(errorBody, HttpStatus.BAD_REQUEST);
	}
	
}
