package com.example.springdemo.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;

@Configuration
public class TokenConfig {

    // 令牌存储策略
    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }
}