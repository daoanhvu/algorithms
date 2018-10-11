package com.bkda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(JPAConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.bkda"})
public class Bkda2Application {

	public static void main(String[] args) {
		SpringApplication.run(Bkda2Application.class, args);
	}
}
