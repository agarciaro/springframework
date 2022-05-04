package com.curso.spring.aop.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.curso.spring.aop.annotation.Cacheame;
import com.curso.spring.aop.exception.CacheException;
import com.curso.spring.aop.service.CacheManager;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
@ConditionalOnMissingBean(name = "cacheManager")
//@ConditionalOnProperty(name = "com.curso.spring.cache.enabled", havingValue = "true")
public class CacheManagementAspect {
	
	@Autowired
	@Qualifier("cacheManager")
	CacheManager cache;
	
	@Around("@annotation(com.curso.spring.aop.annotation.Cacheame)")
	public Object cacheaDatos(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		Cacheame annotation = method.getAnnotation(Cacheame.class);
		String cacheName = annotation.nombre();
		
		try {
			Object value = cache.getCacheValue(cacheName);
			log.info("Cache found! Getting result from cache");
			return value;
		} catch (CacheException e) {
			log.warn("Cache miss: {}", e.getMessage());
		}
		
		Object respuesta = joinPoint.proceed();
		cache.insertValue(cacheName, respuesta);
		
		return respuesta;
		
	}
	
}
