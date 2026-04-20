package com.fooddelivery.fooddeliveryapi.services;

import org.springframework.stereotype.Component;

@Component
public interface JwtService {

    String generateToken(String username, String role);
}
