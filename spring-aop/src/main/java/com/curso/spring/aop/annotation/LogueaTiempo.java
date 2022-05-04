package com.curso.spring.aop.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface LogueaTiempo {
	
	int maxTiempo() default 2000; //Máximo de tiempo permitido para la ejecución del método en ms
	
}
