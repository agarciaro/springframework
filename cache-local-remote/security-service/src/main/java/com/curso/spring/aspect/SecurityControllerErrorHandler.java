package com.curso.spring.aspect;

import javax.security.auth.message.AuthException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.curso.spring.core.model.RestApiError;

@RestControllerAdvice
public class SecurityControllerErrorHandler {
	
	@ExceptionHandler(AuthException.class)
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public RestApiError handleAuthException(AuthException e) {
		return new RestApiError(300, e.getMessage());
	}
	
}
