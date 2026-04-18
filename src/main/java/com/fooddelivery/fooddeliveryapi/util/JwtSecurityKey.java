package com.fooddelivery.fooddeliveryapi.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtSecurityKey {

    private final Key KEY;

    public JwtSecurityKey() {
        KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public Key getKEY() {
        return KEY;
    }
}
