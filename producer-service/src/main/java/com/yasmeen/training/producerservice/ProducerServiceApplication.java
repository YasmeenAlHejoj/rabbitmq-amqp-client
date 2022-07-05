package com.yasmeen.training.producerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ProducerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProducerServiceApplication.class, args);
    }
}
