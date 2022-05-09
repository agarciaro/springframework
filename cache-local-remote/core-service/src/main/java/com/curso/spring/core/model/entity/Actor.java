package com.curso.spring.core.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Actor {
	
//	@SequenceGenerator(name = "seq-actor-generator", allocationSize = 1, initialValue = 0, sequenceName = "seq_Actor")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-actor-generator" )
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 150)
	private String nombre;
	
}
