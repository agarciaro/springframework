package com.curso.spring.aop;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.curso.spring.aop.service.SociosService;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class SpringAopApplication {
	
	@Autowired
	SociosService sociosService;

	public static void main(String[] args) {
		SpringApplication.run(SpringAopApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
		log.info("SociosRaw {}", sociosService.findAll());
		log.info("SociosCached {}", sociosService.findAll());
	}

}
