package com.yasmeen.training.consumerservice;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import com.yasmeen.training.consumerservice.config.prop.RabbitPropConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author YasmeenHj
 * created on 7/5/2022 at 4:59 PM
 * @project rabbit-mq
 */
@Component
@RequiredArgsConstructor
public class Receiver implements CommandLineRunner {
    private final Connection connection;
    private final RabbitPropConfig rabbitPropConfig;

    @Override
    public void run(String... args) throws Exception {
        Channel channel = connection.createChannel();
        for (String queueName : rabbitPropConfig.queueName()) {
            channel.basicConsume(queueName, true, new MyDeliveryCallback(queueName), consumerTag -> {
            });
        }
    }

    @Slf4j
    public record MyDeliveryCallback(String queueName) implements DeliverCallback {
        @Override
        public void handle(String consumerTag, Delivery message) throws IOException {
            String exchange = message.getEnvelope().getExchange();
            String routingKey = message.getEnvelope().getRoutingKey();
            String msg = new String(message.getBody(), StandardCharsets.UTF_8);
            log.info(String.format("message received \n message body : %s \n exchangeName: %s \n queueName : %s \n routKey : %s \n", msg, exchange, queueName, routingKey));
        }
    }
}
