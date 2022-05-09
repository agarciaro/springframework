package com.curso.spring.cache.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.spring.cache.service.CacheManager;

@RestController
@RequestMapping("/api/cache")
@ConditionalOnExpression("${com.curso.spring.cache.enabled} == true and ${com.curso.spring.cache.remote} == true")
public class CacheRestController {
	
	@Autowired
	CacheManager cacheManager;
	
	@DeleteMapping("/{name}")
	public void evict(@PathVariable String name) {
		cacheManager.evict(name);
	}
	
	@DeleteMapping
	public void clear() {
		cacheManager.clear();
	}
	
	@GetMapping("/{name}")
	public Object getCacheValue(@PathVariable String name) {
		return cacheManager.getCacheValue(name);
	}
	
	@GetMapping
	public Set<String> getCacheNames() {
		return cacheManager.getCacheNames();
	}
	
	@PostMapping("/{name}")
	public void insertValue(@PathVariable String name, @RequestBody Object value) {
		cacheManager.insertValue(name, value);
	}
}
