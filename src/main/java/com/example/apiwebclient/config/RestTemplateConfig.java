package com.example.apiwebclient.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Component
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(5)) //Connection Timeout: 5 seconds
                .setReadTimeout(Duration.ofSeconds(5)) //Response Timeout: 5 seconds
                .build();
    }
}
