package com.fooddelivery.fooddeliveryapi.services.Impl;

import com.fooddelivery.fooddeliveryapi.services.JwtService;
import com.fooddelivery.fooddeliveryapi.util.JwtSecurityKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
public class JwtServiceImpl implements JwtService {

    private final JwtSecurityKey jwtSecurityKey;

    public JwtServiceImpl(JwtSecurityKey jwtSecurityKey) {
        this.jwtSecurityKey = jwtSecurityKey;
    }

    @Override
    public String generateToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .addClaims(new HashMap<>())
                .signWith(jwtSecurityKey.getKEY(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims verifySignatureAndExtractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecurityKey.getKEY())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return verifySignatureAndExtractClaims(token).getSubject();
    }

    public Date getExpiration(String token) {
        return verifySignatureAndExtractClaims(token).getExpiration();
    }

    public boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }
}
