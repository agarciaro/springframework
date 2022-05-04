package com.curso.spring.aop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.curso.spring.aop.annotation.Cacheame;
import com.curso.spring.aop.annotation.LogueaTiempo;
import com.curso.spring.aop.model.Socio;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SociosServiceImpl implements SociosService {
	
	private static List<Socio> SOCIOS;
	
	static {
		log.info("init socios");
		SOCIOS = new ArrayList<>();
		SOCIOS.add(new Socio(1L, "Socio1"));
		SOCIOS.add(new Socio(2L, "Socio2"));
		SOCIOS.add(new Socio(3L, "Socio3"));
		SOCIOS.add(new Socio(4L, "Socio4"));
		log.info("fin init socios");
	}
	
//	@LogueaTiempo
	@Cacheame(nombre = "socios")
	@Override
	public List<Socio> findAll() {
		log.info("Obteniendo lista de socios...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			log.error(e.getMessage());
		}
		return SOCIOS;
	}

}
