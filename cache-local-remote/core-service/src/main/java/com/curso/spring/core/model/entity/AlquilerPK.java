package com.curso.spring.core.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlquilerPK implements Serializable {

	private static final long serialVersionUID = 5143271434944832380L;
	
	private Long idEjemplar;
	private Long idSocio;

	private LocalDateTime fechaInicio;
}
