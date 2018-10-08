package com.bkda.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties
@ImportResource("classpath:securityConfiguration.xml")
public class BKDWebSecurityConfig {

}
