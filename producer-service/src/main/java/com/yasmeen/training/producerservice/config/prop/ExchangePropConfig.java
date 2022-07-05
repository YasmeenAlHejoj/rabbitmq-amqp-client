package com.yasmeen.training.producerservice.config.prop;

import java.util.List;

/**
 * @author YasmeenHj
 * created on 7/5/2022 at 3:51 PM
 * @project rabbit-mq
 */
public record ExchangePropConfig(
    String name,
    String type,
    List<BindingPropConfig> binding
) {
}
