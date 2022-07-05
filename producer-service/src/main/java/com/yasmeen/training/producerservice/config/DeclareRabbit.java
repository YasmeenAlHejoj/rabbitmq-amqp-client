package com.yasmeen.training.producerservice.config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yasmeen.training.producerservice.config.prop.BindingPropConfig;
import com.yasmeen.training.producerservice.config.prop.ExchangePropConfig;
import com.yasmeen.training.producerservice.config.prop.RabbitPropConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author YasmeenHj
 * created on 7/5/2022 at 3:40 PM
 * @project rabbit-mq
 */
@Component
@RequiredArgsConstructor
public class DeclareRabbit implements CommandLineRunner {

    private final Connection connection;
    private final RabbitPropConfig rabbitPropConfig;

    @Override
    public void run(String... args) throws Exception {
        Channel channel = connection.createChannel();
        for (ExchangePropConfig ex : rabbitPropConfig.exchange()) {
            channel.exchangeDeclare(ex.name(), ex.type(), true);

            for (BindingPropConfig config : ex.binding()) {
                channel.queueDeclare(config.queue(), true, false, false, null);
                channel.queueBind(
                    config.queue(),
                    ex.name(),
                    config.route(),
                    config.header() == null ? null : new HashMap<>(config.header())
                );
            }
        }
    }
}
