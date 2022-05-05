package com.curso.spring.cache.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConditionalOnProperty(name = "com.curso.spring.cache.enabled", havingValue = "true")
public class CacheConfiguration {
	
	@Value("${com.curso.spring.cache.expiration:20000}")
	private Long expirationTime;
	
	@Value("${com.curso.spring.cache.remote:false}")
	private boolean isRemote;
	
	@Bean
	@ConditionalOnProperty(name = "com.curso.spring.cache.remote", havingValue = "true")
	public RestTemplate cacheRestTemplate() {
		return new RestTemplate();
	}
	
}
