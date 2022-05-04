package com.curso.spring.aop.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CacheEvictedException extends CacheException {
	
	public CacheEvictedException(String message) {
		super(message);
	}
	
}
