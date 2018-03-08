package com.bkda.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

//https://gigsterous.github.io/engineering/2017/03/01/spring-boot-4.html

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Value("${bkda.oauth.tokenTimeout:3600}")
	private int tokenExpiration;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return (new BCryptPasswordEncoder());
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer authConfig) {
//		authConfig.tokenKeyAccess("permitAll()")
//			.checkTokenAccess("isAuthenticated()");
		
		authConfig.checkTokenAccess("isAuthenticated()");
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient("BKDAApplicationID")
			.secret("BKDASecret")
			.authorizedGrantTypes("authorization_code")
			.scopes("user_info")
			.autoApprove(true);
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer authEndpoints) throws Exception {
		authEndpoints.authenticationManager(authenticationManager);
		
		//Set userService
		authEndpoints.userDetailsService(userDetailsService);
	}
}
