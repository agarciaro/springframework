package com.curso.spring.socios.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.curso.spring.cache.annotation.Cacheame;
import com.curso.spring.core.model.QueryParams;
import com.curso.spring.core.model.SocioDto;
import com.curso.spring.core.model.entity.Socio;
import com.curso.spring.core.repository.SociosRepository;
import com.curso.spring.core.service.SociosService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SociosServiceImpl implements SociosService {
	
	private static List<SocioDto> SOCIOS;
	
	@Autowired
	SociosRepository sociosRepository;
	
//	@Autowired
//	@Qualifier("entityManager")
//	EntityManager entityManager;
	
	@Autowired
	QueryParams queryParams;
	
	static {
		log.info("init socios");
		SOCIOS = new ArrayList<>();
		SOCIOS.add(new SocioDto(1L, "Socio1"));
		SOCIOS.add(new SocioDto(2L, "Socio2"));
		SOCIOS.add(new SocioDto(3L, "Socio3"));
		SOCIOS.add(new SocioDto(4L, "Socio4"));
		log.info("fin init socios");
	}
	
//	@LogueaTiempo
	@Cacheame(nombre = "socios")
	@Override
	public List<SocioDto> findAllMock() {
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

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED )
	@Override
	public List<SocioDto> findAll() {
		List<Socio> sociosEntity = sociosRepository.findAll();
		return sociosEntity.stream().map(e -> new SocioDto(e.getId(), e.getNombre())).collect(Collectors.toList());
	}

}
