package com.yasmeen.training.producerservice.dto;

import java.util.Map;

/**
 * @author YasmeenHj
 * created on 7/5/2022 at 4:13 PM
 * @project rabbit-mq
 */
public record NewMessageDto(
    String exchange,
    String route,
    String message
) {
}
