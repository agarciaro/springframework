package com.curso.spring.core.model.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pelicula {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String titulo;
	@Lob
	private String sinposis;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "pelicula_director",
		joinColumns = @JoinColumn(name = "id_pelicula", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "id_director", referencedColumnName = "id")
	)
	private Set<Director> directores;
	
}
