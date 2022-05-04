package com.curso.spring.aop.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CacheExpiredException extends CacheException {
	public CacheExpiredException(String message) {
		super(message);
	}
}
