package com.curso.spring.core.model.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alquiler {
	private AlquilerPK id;
	
	private LocalDateTime fechaDevolucion;
}
