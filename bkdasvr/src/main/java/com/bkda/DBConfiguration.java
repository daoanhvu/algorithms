package com.bkda;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//		entityManagerFactoryRef="mySqlEntityManagerFactory",
//		transactionManagerRef="mySqlTransactionManager"
//		)
public class DBConfiguration {
	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource mySqlDatasource() {
		return DataSourceBuilder.create().build();
	}
}
