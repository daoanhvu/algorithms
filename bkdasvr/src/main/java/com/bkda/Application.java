package com.bkda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.bkda.config.DBConfiguration;

@SpringBootApplication(scanBasePackages={
		"com.bkda.config",
		"com.bkda.controller",
		"com.bkda.service"
		})
@EnableJpaRepositories(basePackages= {"com.bkda.repository"})
@Import(DBConfiguration.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
