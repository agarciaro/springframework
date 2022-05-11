package com.curso.spring.core.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.curso.spring.core.model.entity.Socio;

@Repository
public class SociosJdbcRepositoryImpl implements SociosJdbcRepository {
	
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<Socio> findAll() {
		String sql = "SELECT * FROM socio";
		
//		return jdbcTemplate.query(sql, new RowMapper() {
//
//			@Override
//			public Socio mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Socio socio = Socio.builder().id(rs.getLong("id")).nombre(rs.getString("nombre"))
//						.dni(rs.getString("dni")).referidoPor(rs.getLong("referido_por")).build();
//				return socio;
//			}
//
//		});
		
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Socio.class));
	}

	@Override
	public List<Socio> findByNombre(String nombre) {
		String sql = "SELECT * FROM socio WHERE nombre = :nombre";
		
		MapSqlParameterSource params = new MapSqlParameterSource("nombre", nombre);
		
		return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(Socio.class));
	}

	@Override
	public Socio insert(Socio socio) {
		String sql = "INSERT INTO (nombre, dni, referido_por) VALUES (:nombre, :dni, :referido_por)";
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(socio);
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(sql, params, keyHolder);
		
		socio.setId(keyHolder.getKeyAs(Long.class));

		return socio;
	}

}
