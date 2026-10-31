package com.example.pharmacyservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        // Cấu hình timeout 1.5 giây để tránh chờ đợi lâu khi kho tổng bị sập
        factory.setConnectTimeout(1500);
        factory.setReadTimeout(1500);
        return new RestTemplate(factory);
    }
}
