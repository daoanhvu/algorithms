package com.bkda.config;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//	private AuthenticationManager authenticationManager;
	
	public SecurityConfig() {
		super(true);
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception { // @formatter:off
        http.requestMatchers()
            .antMatchers("/login", "/oauth/authorize")
            .and()
            .authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .permitAll();
    } // @formatter:on

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // @formatter:off
//    	auth.parentAuthenticationManager(authenticationManager);
//        auth.parentAuthenticationManager(authenticationManager)
//            .inMemoryAuthentication()
//            .withUser("admin")
//            .password("admin123")
//            .roles("USER");
    	
//    } // @formatter:on
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
}
