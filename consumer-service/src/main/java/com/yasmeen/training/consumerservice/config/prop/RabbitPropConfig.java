package com.yasmeen.training.consumerservice.config.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author YasmeenHj
 * created on 7/5/2022 at 4:45 PM
 * @project rabbit-mq
 */
@ConfigurationProperties(value = "rabbit-config")
public record RabbitPropConfig(
    String host,
    List<String> queueName
) {
}
