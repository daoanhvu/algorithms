package com.bkda.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages="com.bkda.dao",
		entityManagerFactoryRef="entityManagerFactory", 
		transactionManagerRef="transactionManager")
//@PropertySource("classpath:application.yml")
@EnableTransactionManagement
public class JPAConfiguration {
	
	private static final Logger logger = LoggerFactory.getLogger(JPAConfiguration.class);
	
	@Autowired
	Environment environment;
	
	@Value("${datasource.bkda.maxPoolSize:10}")
	private int maxPoolSize;
	
	/**
	 * Populate SpringBoot DataSourceProperties object directly from application.yml
	 * base on prefix
	 * @return
	 */
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
	
		//Set timeout to 5 seconds
//		dataSource.setLoginTimeout(5000);
		return dataSource;
	}	
	
	/**
	 * Setup Entity Manager Factory
	 * @return
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean localEntityFactory = new LocalContainerEntityManagerFactoryBean();
		localEntityFactory.setDataSource(dataSource());
		localEntityFactory.setPackagesToScan(new String[]{"com.bkda.entity"});
		localEntityFactory.setJpaVendorAdapter(jpaVendorAdapter());
		localEntityFactory.setJpaProperties(jpaProperties());
		return localEntityFactory;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
//		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
//		return jpaVendorAdapter;
		return new HibernateJpaVendorAdapter();
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
