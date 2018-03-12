package com.bkda.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableOAuth2Sso
public class BkdaWebSecurityConfigurer extends WebSecurityConfigurerAdapter {
	@Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/api/**/*")
          .authorizeRequests()
          .antMatchers("/", "/**/*.css", "/**/*.js", "/login**")
          .permitAll()
          .anyRequest()
          .authenticated();
    }
}
