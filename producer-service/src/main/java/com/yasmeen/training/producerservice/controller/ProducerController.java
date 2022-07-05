package com.yasmeen.training.producerservice.controller;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yasmeen.training.producerservice.config.prop.RabbitPropConfig;
import com.yasmeen.training.producerservice.dto.NewMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YasmeenHj
 * created on 7/5/2022 at 3:03 PM
 * @project rabbit-mq
 */

@Slf4j
@RestController
@RequestMapping("rabbit")
@RequiredArgsConstructor
public class ProducerController {
    private final Connection connection;
    private final RabbitPropConfig rabbitPropConfig;

    @GetMapping
    public RabbitPropConfig getConfig() {
        return rabbitPropConfig;
    }

    @PostMapping
    public boolean sendMessage(@RequestBody NewMessageDto body, @RequestHeader Map<String, String> headers) {
        try {
            Channel channel = connection.createChannel();
            AMQP.BasicProperties prop = new AMQP.BasicProperties().builder().headers(new HashMap<>(headers)).build();
            channel.basicPublish(
                body.exchange(),
                body.route(),
                prop,
                body.message().getBytes(StandardCharsets.UTF_8)
            );
            return true;
        } catch (IOException e) {
            log.error("", e);
            return false;
        }
    }
}
