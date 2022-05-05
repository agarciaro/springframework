package com.curso.spring.cache.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CacheExpiredException extends CacheException {
	public CacheExpiredException(String message) {
		super(message);
	}
}
