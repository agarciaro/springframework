package com.curso.spring.aop.service;

public interface CacheManager {
	
	public Object getCacheValue(String cacheName);
	
	public void insertValue(String cacheName, Object value);
	
	public void evict (String cacheName);
	
	public void remove(String cacheName);
	
	public void clear();
	
	public Long getExpirationTime();
}
