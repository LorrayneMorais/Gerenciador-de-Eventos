package com.neki.gerenciador.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private String jwtSecret = "secretkey"; // Sua chave secreta
    private int jwtExpirationMs = 86400000; // 24 horas

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public boolean validateJwtToken(String jwt) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(jwt);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getUserNameFromJwtToken(String jwt) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(jwt)
            .getBody();
        return claims.getSubject();
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)  // Altere o parâmetro para username
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }


    public String extractUsername(String token) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
        return claims.getSubject();  // Retorna o username que está no subject do token
    }

}
