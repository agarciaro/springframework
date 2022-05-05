package com.curso.spring.cache.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CacheNotFoundException extends CacheException {
	
	public CacheNotFoundException(String message) {
		super(message);
	}
}
