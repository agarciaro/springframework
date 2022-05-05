package com.curso.spring.cache.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CacheEvictedException extends CacheException {
	
	public CacheEvictedException(String message) {
		super(message);
	}
	
}
