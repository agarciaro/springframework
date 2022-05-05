package com.curso.spring.cache.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.curso.spring.cache.exception.CacheException;
import com.curso.spring.cache.exception.CacheNotFoundException;
import com.curso.spring.cache.model.CacheMode;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@NoArgsConstructor
@ConditionalOnExpression("${com.curso.spring.cache.enabled} == true and ${com.curso.spring.cache.remote} == true")
@Slf4j
public class RemoteCacheManager implements CacheManager {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${com.curso.spring.cache.base-url}")
	String baseUrl;
	
	@Override
	public Object getCacheValue(String cacheName) {
		String url = baseUrl + "/" + cacheName;
		
		try {
			return restTemplate.getForObject(url, Object.class);
		} catch (RestClientException e) {
			throw new CacheException("Cache no encontrada o inválida");
		}

	}

	@Override
	@Async("cacheExecutor")
	public void insertValue(String cacheName, Object value) {
		log.info("insertValue REST");
		String url = baseUrl + "/" + cacheName;
		restTemplate.postForEntity(url, value, String.class);

	}

	@Override
	public void evict(String cacheName) {
		String url = baseUrl + "/" + cacheName;
		
		try {
			restTemplate.delete(url);
		} catch (RestClientException e) {
			throw new CacheNotFoundException("Cache no encontrada ");
		}

	}

	@Override
	public void remove(String cacheName) {
		//TODO implementar remove
	}

	@Override
	public void clear() {
		restTemplate.delete(baseUrl);
	}

	@Override
	public Long getExpirationTime() {
		return 0L; //TODO implementar getExpirationTime
	}

	@Override
	public Set<String> getCacheNames() {
		return restTemplate.getForObject(baseUrl, Set.class);
	}

	@Override
	public String getMode() {
		return CacheMode.REMOTE.getMode();
	}

}
