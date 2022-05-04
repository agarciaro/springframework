package com.curso.spring.aop.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.curso.spring.aop.annotation.LogueaTiempo;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class MonitorTiempoEjecucionAspect {
	
	@Around("@annotation(com.curso.spring.aop.annotation.LogueaTiempo)")
	public Object logueaTiempoEjecucion(ProceedingJoinPoint joinPoint) throws Throwable {
		
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		LogueaTiempo annotation = method.getAnnotation(LogueaTiempo.class);
		
		long tiempoInicial = System.currentTimeMillis();
		Object response = joinPoint.proceed();
		long tiempoEjecucion = System.currentTimeMillis() - tiempoInicial;
		
		if(tiempoEjecucion > annotation.maxTiempo()) {
			log.error("Tiempo ejecución excedido! (max={}, actual={})", annotation.maxTiempo(), tiempoEjecucion);
		}else {
			log.info("Tiempo de ejecución: {}ms", tiempoEjecucion);
		}
		
		return response;
		
	}
	
}
