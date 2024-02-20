package com.scaler.productService.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TokenValidator {
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    public TokenValidator(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }
    public Optional<JwtData> validateToken(String token) {
        // Use the Rest template to call the user service and validate the token
        return Optional.empty();
    }
}
