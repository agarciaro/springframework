package com.curso.spring.cache.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class CacheElement {
	@Setter
	private Object element;
	@Setter
	private boolean valid = true;
	private final Long creationTime = System.currentTimeMillis();
	
	public CacheElement(Object e) {
		this.element = e;
	}
}
