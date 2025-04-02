package com.example.lab3.config;

import com.example.lab3.model.Location;
import com.example.lab3.model.SunriseSunset;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Map<String, List<Location>> locationCache() {
        return new HashMap<>();
    }

    @Bean
    public Map<String, List<SunriseSunset>> sunriseSunsetCache() {
        return new HashMap<>();
    }
}