package com.curso.spring.core.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pelicula {

	private Long id;
	
	private String titulo;

	private String sinposis;
	
}
