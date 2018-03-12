package com.bkda.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

// https://gigsterous.github.io/engineering/2017/03/01/spring-boot-4.html
// http://www.baeldung.com/rest-api-spring-oauth2-angularjs

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
    private DataSource dataSource;
	
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
		authConfig.tokenKeyAccess("permitAll()")
			.checkTokenAccess("isAuthenticated()");		
//		authConfig.checkTokenAccess("isAuthenticated()");
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		clients.inMemory()
//			.withClient("BKDAApplicationID")
//			.secret("BKDASecret")
//			.authorizedGrantTypes("password","authorization_code")
//			.scopes("user_info")
//			.autoApprove(true);
		
		clients.jdbc(dataSource);
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer authEndpoints) throws Exception {
		
		authEndpoints.tokenStore(tokenStore()).
//			tokenServices(tokenServices()).
			authenticationManager(authenticationManager);
		
		//Set userService
		authEndpoints.userDetailsService(userDetailsService);
	}
	
	@Bean
	@Primary
	@ConfigurationProperties("datasource.bkda")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}
//	@Bean
//	public DataSource dataSource1() {
//		DataSource dataSource;
//		DataSourceProperties dsProperties = dataSourceProperties();
//		dataSource = DataSourceBuilder.create(dsProperties.getClassLoader())
//				.driverClassName(dsProperties.getDriverClassName())
//				.url(dsProperties.getUrl())
//				.username(dsProperties.getUsername())
//				.password(dsProperties.getPassword())
//				.type(dsProperties.getType()).build();
//		return dataSource;
//	}
	
	@Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }
	
	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}
}
