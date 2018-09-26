package com.bkda.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.bkda.common.Constants;

/**
 * 
 * @author Dao Anh Vu
 *
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef="entityManagerFactory",
		transactionManagerRef="transactionManagerFactory"
		)
public class DBConfiguration {
	
	private static Logger log = LoggerFactory.getLogger(DBConfiguration.class);
	
	@Autowired
	private Environment env;
	
	@Bean
    public DataSource dataSource() {
        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setMaxActive(Integer.parseInt(env.getProperty("bkda.datasource.tomcat.max-active")));
        poolProperties.setMaxWait(Integer.parseInt(env.getProperty("bkda.datasource.tomcat.max-wait")));
        // Initial Size is optional property. Will use the default initial size otherwise which is reasonable.
        String is = env.getProperty("bkda.datasource.tomcat.initialSize");
        if (!StringUtils.isEmpty(is)) {
            try {
                poolProperties.setInitialSize(Integer.parseInt(is));
            } catch (Exception exc) {
                log.info("InitalSize property was not valid.", exc.getMessage());
            }
        }
        poolProperties
                .setTestOnBorrow(Boolean.parseBoolean(env.getProperty("bkda.datasource.tomcat.test-on-borrow")));
        poolProperties.setDriverClassName(env.getProperty("bkda.datasource.driverClassName"));
        poolProperties.setUrl(env.getProperty("bkda.datasource.url"));
        poolProperties.setUsername(env.getProperty("bkda.datasource.username"));
        poolProperties.setPassword(env.getProperty("bkda.datasource.password"));
        poolProperties.setValidationQuery(env.getProperty("bkda.datasource.validation-query"));
        DataSource dataSource = new DataSource();
        dataSource.setPoolProperties(poolProperties);
        return dataSource;
    }
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		Database database = Database.MYSQL;
		String dbType = env.getProperty("bkda.datasource.type");
		if(Constants.DBTYPE_H2.equalsIgnoreCase(dbType)) {
            database = Database.H2;
        } else if(Constants.DBTYPE_POSTGRES.equalsIgnoreCase(dbType)) {
            database = Database.POSTGRESQL;
        }
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(database);
        vendorAdapter.setShowSql(false);
        return vendorAdapter;
	}
	
	@Primary
	@Bean(name = "entityManagerFactory")
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean localEntityFactory = new LocalContainerEntityManagerFactoryBean();
		localEntityFactory.setDataSource(dataSource());
		localEntityFactory.setPackagesToScan(new String[]{"com.bkda.model"});
		localEntityFactory.setJpaVendorAdapter(jpaVendorAdapter());
		localEntityFactory.setJpaProperties(jpaProperties());
		localEntityFactory.setPersistenceUnitName("bkda");
		localEntityFactory.afterPropertiesSet();
		return localEntityFactory.getObject();
	}
	
	private Properties jpaProperties() {
		Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getRequiredProperty("spring.jpa.hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("spring.jpa.hibernate.hbm2ddl"));
        properties.put("hibernate.show_sql", env.getRequiredProperty("spring.jpa.hibernate.show_sql"));
        properties.put("hibernate.format_sql", env.getRequiredProperty("spring.jpa.hibernate.format_sql"));
        String defaultSchema = env.getProperty("bkda.datasource.defaultSchema");
        if( StringUtils.isNotEmpty(defaultSchema) ){
            properties.put("hibernate.default_schema", defaultSchema);
        }
        return properties;
	}
	
	@Bean(name = "transactionManagerFactory")
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
	}
}
