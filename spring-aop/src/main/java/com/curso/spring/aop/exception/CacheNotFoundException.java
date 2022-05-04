package com.curso.spring.aop.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CacheNotFoundException extends CacheException {
	
	public CacheNotFoundException(String message) {
		super(message);
	}
}
