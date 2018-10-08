package com.bkda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * In the OAuth 2.0 Scenario, this is Client Application 
 * @author davu
 */

@SpringBootApplication(scanBasePackages={
		"com.bkda", 
		"com.bkda.service", 
		"com.bkda.config",
		"com.bkda.controller"
		})
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CorsFilter corsFilter() {
		final String asterisk = "*";
		final UrlBasedCorsConfigurationSource corsSrc = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowCredentials(true);
		corsConfig.addAllowedOrigin(asterisk);
		corsConfig.addAllowedHeader(asterisk);
		corsConfig.addAllowedMethod(asterisk);
		corsSrc.registerCorsConfiguration("/**", corsConfig);
		return new CorsFilter(corsSrc);
	}
}
