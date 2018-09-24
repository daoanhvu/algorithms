package com.bkda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * In the OAuth 2.0 Scenario, this is Client Application 
 * @author davu
 */

@SpringBootApplication(scanBasePackages={"com.bkda", "com.bkda.config"})
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
