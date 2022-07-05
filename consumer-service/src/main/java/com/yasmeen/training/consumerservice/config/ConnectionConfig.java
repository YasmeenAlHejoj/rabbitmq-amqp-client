package com.yasmeen.training.consumerservice.config;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.yasmeen.training.consumerservice.config.prop.RabbitPropConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author YasmeenHj
 * created on 7/5/2022 at 4:42 PM
 * @project rabbit-mq
 */
@Configuration
public class ConnectionConfig {

    @Bean
    public Connection connection(RabbitPropConfig rabbitPropConfig) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(rabbitPropConfig.host());
        return connectionFactory.newConnection();
    }
}