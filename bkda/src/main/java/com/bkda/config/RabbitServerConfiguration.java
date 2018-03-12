package com.bkda.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConfigurationProperties(prefix="amqp")
@Import(PropertyPlaceholderConfiguration.class)
public class RabbitServerConfiguration {
	
	public static final String EXCHANGE_NAME = "BKDA_FINANCE";
	public static final String QUEUE_NAME = "BKDA_FINANCE_QUEUE";
	public static final String ROUTING_KEY = "BKDA_FINANCE_ROUTING_KEY";
	
//	@Value("${amqp.port:5672}")
	private int port;
	
//	@Value("${amqp.username:davu}")
	private String username;
	
//	@Value("${amqp.password:123456}")
	private String password;
	
//	@Value("${amqp.vhost:/}")
	private String vhost;
	
	//@Value("${amqp.host:localhost}")
//	@Value("${amqp.host:192.168.1.106}")
	private String host;
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVhost() {
		return vhost;
	}

	public void setVhost(String vhost) {
		this.vhost = vhost;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		connectionFactory.setPort(port);
		connectionFactory.setVirtualHost(vhost);
		connectionFactory.setHost(host);
		return connectionFactory;
	}
	
	@Bean
	public TopicExchange getTopicExchange() {
		return new TopicExchange(EXCHANGE_NAME, false, false);
	}
	
	@Bean
	public AmqpAdmin amqpAdmin() {
		RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
		rabbitAdmin.setAutoStartup(true);
		return rabbitAdmin;
	}
	
	@Bean
	public Queue queue() {
		return new Queue(QUEUE_NAME);
	}
	
	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(getTopicExchange()).with(ROUTING_KEY);
	}

	@Bean
	public MessageReceiver messageListener() {
		return new MessageReceiver();
	}

	@Bean
	public MessageListenerAdapter listenerAdapter(MessageReceiver receiver) {
		MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(receiver,
				"receiveMessage");
//		listenerAdapter.setDefaultListenerMethod("handleLog");
		
		return listenerAdapter;
	}
	
	@Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }
}

@Configuration
class PropertyPlaceholderConfiguration {
	@Bean
	public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
		return new PropertyPlaceholderConfigurer();
	}
}