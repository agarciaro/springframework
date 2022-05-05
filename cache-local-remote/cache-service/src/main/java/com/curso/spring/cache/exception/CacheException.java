package com.curso.spring.cache.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CacheException extends RuntimeException {
	
	protected String message = "Error en cache";
	
}
