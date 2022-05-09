package com.curso.spring.cache.aspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.curso.spring.cache.exception.CacheException;
import com.curso.spring.cache.exception.CacheExpiredException;
import com.curso.spring.cache.exception.CacheNotFoundException;
import com.curso.spring.cache.service.CacheManager;
import com.curso.spring.core.model.RestApiError;

@RestControllerAdvice
@ConditionalOnProperty(name = "com.curso.spring.cache.enabled", havingValue = "true")
public class CacheRestErrorHandler {
	
	@Autowired
	CacheManager cacheManager;
	
	@ExceptionHandler({CacheNotFoundException.class})
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public RestApiError handleCacheNotFound(CacheNotFoundException e) {
		RestApiError error = new RestApiError(100, "Cache not found: " + e.getMessage() + " - MODE: " + cacheManager.getMode());
		return error;
	}
	
	@ExceptionHandler({CacheExpiredException.class})
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public RestApiError handleCacheGeneric(CacheExpiredException e) {
		RestApiError error = new RestApiError(101, "Error (Expirado): " + e.getMessage() + " - MODE: " + cacheManager.getMode());
		return error;
	}
	
	@ExceptionHandler({CacheException.class, Exception.class})
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public RestApiError handleCacheGeneric(Exception e) {
		RestApiError error = new RestApiError(0, "Error: " + e.getMessage() + " - MODE: " + cacheManager.getMode());
		return error;
	}
	
	
	
}
