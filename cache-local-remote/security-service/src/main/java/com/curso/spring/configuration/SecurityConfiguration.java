package com.curso.spring.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.curso.spring.core.model.JwtToken;
import com.curso.spring.filter.JwtTokenFilter;
import com.curso.spring.service.UsuarioDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private UsuarioDetailsService usuarioDetailsService;
	
	@Autowired
	private JwtTokenFilter jwtTokenFilter;
	
	@Autowired
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Scope(scopeName =  "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
	JwtToken jwtToken() {
		return new JwtToken();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Habilitar CORS y deshabilitar CSRF
		http = http.cors().and().csrf().disable();
		
		//Establecer el Session Management a STATELESS
		http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
		
		http.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/api/login").permitAll() //Permitir acceso al endpoint login
			.antMatchers(HttpMethod.POST, "/api/registro").permitAll() //Permitir acceso al endpoint registro
			.anyRequest().authenticated();
		
		//Añadir el filtro para procesar el token JWT y configurar la autenticación
		http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	
	
}
