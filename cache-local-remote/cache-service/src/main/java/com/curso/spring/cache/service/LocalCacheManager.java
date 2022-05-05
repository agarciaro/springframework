package com.curso.spring.cache.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

import com.curso.spring.cache.exception.CacheEvictedException;
import com.curso.spring.cache.exception.CacheExpiredException;
import com.curso.spring.cache.exception.CacheNotFoundException;
import com.curso.spring.cache.model.CacheElement;
import com.curso.spring.cache.model.CacheMode;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@NoArgsConstructor
@ConditionalOnExpression("${com.curso.spring.cache.enabled} == true and ${com.curso.spring.cache.remote} == false")
@Slf4j
public class LocalCacheManager implements CacheManager {
	
	private Map<String, CacheElement> container = new HashMap<>();
	
	@Getter
	private Long expirationTime = 30L * 1000; //En milisegundos

	public LocalCacheManager(Long expirationTime) {
		super();
		this.expirationTime = expirationTime;
	}
	
	private CacheElement getCache(String cacheName) throws CacheNotFoundException, CacheExpiredException, CacheEvictedException {
		CacheElement element = container.get(cacheName);
		
		if(element == null) {
			throw new CacheNotFoundException();
		}
		
		if(isExpired(element)) {
			throw new CacheExpiredException();
		}
		
		if(element.isValid() == false) {
			throw new CacheEvictedException();
		}
		
		return element;
	}
	
	private boolean isExpired(CacheElement element) {
		long diff = System.currentTimeMillis() - element.getCreationTime();
		return (diff > this.expirationTime)?true:false;
	}
	
	@Override
	public Object getCacheValue(String cacheName) throws CacheNotFoundException, CacheExpiredException, CacheEvictedException {
		return getCache(cacheName).getElement();
	}
	
	@Override
	public void insertValue(String cacheName, Object value) {
		container.put(cacheName, new CacheElement(value));
	}
	
	@Override
	public void evict (String cacheName) {
		CacheElement element = null;
		
		try {
			element = getCache(cacheName);
		} catch (CacheEvictedException e) {
			return;
		}
		
		element.setValid(false);
	}
	
	@Override
	public void remove(String cacheName) {
		container.remove(cacheName);
	}
	
	@Override
	public void clear() {
		container.clear();
	}

	@Override
	public Set<String> getCacheNames() {
		return container.keySet();
	}

	@Override
	public String getMode() {
		return CacheMode.LOCAL.getMode();
	}
	
}
