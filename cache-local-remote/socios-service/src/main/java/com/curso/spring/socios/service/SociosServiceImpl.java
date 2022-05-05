package com.curso.spring.socios.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.spring.cache.annotation.Cacheame;
import com.curso.spring.core.model.QueryParams;
import com.curso.spring.core.model.Socio;
import com.curso.spring.core.service.SociosService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SociosServiceImpl implements SociosService {
	
	private static List<Socio> SOCIOS;
	
	@Autowired
	QueryParams queryParams;
	
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
		
		try {
			return SOCIOS.subList(0, queryParams.getLimit());
		} catch (Exception e) {
			return SOCIOS.subList(0, SOCIOS.size());
		}
	}

}
