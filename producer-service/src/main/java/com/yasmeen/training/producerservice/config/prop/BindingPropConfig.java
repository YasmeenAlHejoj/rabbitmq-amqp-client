package com.yasmeen.training.producerservice.config.prop;

import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Map;

/**
 * @author YasmeenHj
 * created on 7/5/2022 at 3:52 PM
 * @project rabbit-mq
 */
public record BindingPropConfig(
    String queue,

    @DefaultValue("") String route,

    Map<String, String> header
) {
}
