package com.fooddelivery.fooddeliveryapi.service;

import org.springframework.stereotype.Component;

@Component
public interface JwtService {

    String generateToken(String username, String role);
}
