package com.curso.spring.socios.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import com.curso.spring.core.model.QueryParams;

@Configuration
public class WebConfiguration {
	
	@Bean("queryParams")
	@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public QueryParams queryParams() {
		return new QueryParams();
	}
	
}
