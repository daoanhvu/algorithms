package com.bkda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.bkda.config.DBConfiguration;

@Import(DBConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.bkda", 
		"com.bkda.config", "com.bkda.controller","com.bkda.service", "com.bkda.repository"})
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
}
