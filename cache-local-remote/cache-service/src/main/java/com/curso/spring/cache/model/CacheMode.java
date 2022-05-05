package com.curso.spring.cache.model;

import lombok.Getter;

public enum CacheMode {
	LOCAL("Local"), REMOTE("Remote");
	
	@Getter
	private String mode;
	
	CacheMode(String mode) {
		this.mode = mode;
	}
}
