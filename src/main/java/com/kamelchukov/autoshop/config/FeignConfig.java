package com.kamelchukov.autoshop.config;

import com.kamelchukov.autoshop.service.KeycloakService;
import feign.RequestInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class FeignConfig {

    private KeycloakService keycloakService;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> template.header(
                "Authorization", keycloakService.getTokenViaKeycloakTokenManager());
    }
}
