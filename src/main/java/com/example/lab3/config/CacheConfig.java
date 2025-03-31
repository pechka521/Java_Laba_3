package com.example.lab3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class CacheConfig {
    @Bean
    public Map<String, Object> cacheManager() {
        return new ConcurrentHashMap<>();
    }
}