package com.curso.spring.aop.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.curso.spring.aop.service.CacheManager;
import com.curso.spring.aop.service.DatabaseCacheManager;
import com.curso.spring.aop.service.InMemoryCacheManager;

@Configuration
@ConditionalOnProperty(name = "com.curso.spring.cache.enabled", havingValue = "true")
public class CacheConfiguration {
	
	@Value("${com.curso.spring.cache.expiration:20000}")
	private Long expirationTime;
	
	@Bean("cacheManager")
	@ConditionalOnProperty(name = "com.curso.spring.cache.type", havingValue = "IN_MEMORY")
	public CacheManager inMemoryCacheManager() {
		CacheManager cache = new InMemoryCacheManager(expirationTime);
		return cache;
	}
	
	@Bean("cacheManager")
	@ConditionalOnProperty(name = "com.curso.spring.cache.type", havingValue = "DATABASE")
	public CacheManager databaseCacheManager() {
		CacheManager cache = new DatabaseCacheManager(expirationTime);
		return cache;
	}
}
