package com.curso.spring;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.curso.spring.core.service.SociosService;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class SociosApplication {
	
	@Autowired
	SociosService sociosService;
	
	public static void main(String[] args) {
		SpringApplication.run(SociosApplication.class, args);
	}

}
