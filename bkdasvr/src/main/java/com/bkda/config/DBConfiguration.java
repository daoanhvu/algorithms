package com.bkda.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef="entityManagerFactory",
		transactionManagerRef="transactionManager"
		)
public class DBConfiguration {
	
	@Autowired
	Environment environment;
	
	@Bean
	@Primary
	@ConfigurationProperties("datasource.bkda")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean
	public DataSource dataSource() {
		DataSource dataSource;
		DataSourceProperties dsProperties = dataSourceProperties();
		dataSource = DataSourceBuilder.create(dsProperties.getClassLoader())
				.driverClassName(dsProperties.getDriverClassName())
				.url(dsProperties.getUrl())
				.username(dsProperties.getUsername())
				.password(dsProperties.getPassword())
				.type(dsProperties.getType()).build();
		return dataSource;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean localEntityFactory = new LocalContainerEntityManagerFactoryBean();
		localEntityFactory.setDataSource(dataSource());
		localEntityFactory.setPackagesToScan(new String[]{"com.bkda.model"});
		localEntityFactory.setJpaVendorAdapter(jpaVendorAdapter());
		localEntityFactory.setJpaProperties(jpaProperties());
		return localEntityFactory;
	}
	
	private Properties jpaProperties() {
		Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("datasource.bkda.hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("datasource.bkda.hibernate.hbm2ddl.method"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("datasource.bkda.hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("datasource.bkda.hibernate.format_sql"));
        if(!isEmpty(environment.getRequiredProperty("datasource.bkda.defaultSchema"))){
            properties.put("hibernate.default_schema", environment.getRequiredProperty("datasource.bkda.defaultSchema"));
        }
        return properties;
	}
	
	/**
	 * Utility method, it should not be here
	 */
	private boolean isEmpty(CharSequence cs) {
		return cs == null || cs.length() <= 0;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
	}
}
