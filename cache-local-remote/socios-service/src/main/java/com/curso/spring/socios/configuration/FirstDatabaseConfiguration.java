package com.curso.spring.socios.configuration;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.TransactionManager;

//@Configuration
//@EnableJpaRepositories(
//		basePackages = {"com.curso.spring.core.model.entity", "com.curso.spring.core.repository"},
//		entityManagerFactoryRef = "entityManager",
//		transactionManagerRef = "transactionManager"
//)
public class FirstDatabaseConfiguration {
	
	@Bean("dataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean("entityManager")
	public LocalContainerEntityManagerFactoryBean entityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] {"com.curso.spring.core.model.entity", "com.curso.spring.core.repository"});
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "");
		properties.put("hibernate.dialect", "");
		//....
		em.setJpaPropertyMap(properties);
		
		return em;
	}
	
	@Bean("transactionManager")
	public TransactionManager transactionManager() {
		return new JpaTransactionManager(null);
	}
	
}
