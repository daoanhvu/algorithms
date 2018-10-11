package com.bkda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * In the OAuth 2.0 Scenario, this is Client Application 
 * @author davu
 */

@SpringBootApplication(scanBasePackages={
		"com.bkda", 
		"com.bkda.service",
		"com.bkda.gateway",
		"com.bkda.config",
		"com.bkda.controller"
		})
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
