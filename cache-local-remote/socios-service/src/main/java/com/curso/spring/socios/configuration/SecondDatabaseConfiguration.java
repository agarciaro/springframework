package com.curso.spring.socios.configuration;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@Configuration
//@EnableJpaRepositories(
//		basePackages = "com.curso.spring.core.model.secondentity",
//		entityManagerFactoryRef = "secondEntityManager",
//		transactionManagerRef = "secondTransactionManager"
//)
public class SecondDatabaseConfiguration {
	
	@Bean("second-dataSource")
	@ConfigurationProperties(prefix = "spring.second-datasource")
	DataSource secondDataSource() {
		return DataSourceBuilder.create().build();
	}
	
}
