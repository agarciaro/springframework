package com.curso.spring.socios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curso.spring.core.model.JwtToken;
import com.curso.spring.core.model.QueryParams;
import com.curso.spring.core.model.SocioDto;
import com.curso.spring.core.model.entity.Socio;
import com.curso.spring.core.repository.SociosRepository;
import com.curso.spring.core.service.SociosService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/socios")
public class SociosRestController {
	
	@Autowired
	QueryParams queryParams;
	
	@Autowired
	SociosService sociosService;
	
	@Autowired
	SociosRepository sociosRepository; //KAKA
	
	@Autowired
	JwtToken jwtToken;
	
	@Operation(summary = "Devuelve los socios de la Base de Datos con un límite por defecto de 10")
	@GetMapping
	public List<SocioDto> findByFilter(@RequestParam(required = false) Integer limit) {
		queryParams.setLimit(limit);

		return sociosService.findAll();
	}
	
	@GetMapping("/self")
	public Socio findSocioById() {
		
		return sociosRepository.findById(jwtToken.getIdUsuario()).get(); //FIXME el token devuelve idUsuario no idSocio
	}
	
}
