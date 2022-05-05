package com.curso.spring.cache.service;

import java.util.Set;

public interface CacheManager {
	
	public Object getCacheValue(String cacheName);
	
	public void insertValue(String cacheName, Object value);
	
	public void evict (String cacheName);
	
	public void remove(String cacheName);
	
	public void clear();
	
	public Long getExpirationTime();
	
	public Set<String> getCacheNames();
	
	public String getMode();
}
