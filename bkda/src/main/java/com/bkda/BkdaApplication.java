package com.bkda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.bkda.config.JPAConfiguration;


@Import(JPAConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.bkda", "com.bkda.config"})
public class BkdaApplication {
	public static void main(String[] args) {
		SpringApplication.run(BkdaApplication.class, args);
	}
}
