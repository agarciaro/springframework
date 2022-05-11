package com.curso.spring.core.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Socio {

	private Long id;

	private String nombre;

	private String dni;
	
	private Long referidoPor;
}
