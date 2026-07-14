package com.fooddelivery.fooddeliveryapi.service;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

@Component
public interface JwtService {

    String generateToken(String username, String role);
    Claims verifySignatureAndExtractClaims(String token);
}
