package com.uca.parcialfinalncapas.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtService {

        private final String SECRET_KEY = "mi-clave-secreta-segura";

        public String generateToken(UserDetails userDetails) {
            return Jwts.builder()
                    .setSubject(userDetails.getUsername())
                    .claim("roles", userDetails.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                    .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                    .compact();
        }

        public String extractUsername(String token) {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }

        public List<String> extractRoles(String token) {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.get("roles", List.class);
        }

        public boolean validateToken(String token, UserDetails userDetails) {
            return extractUsername(token).equals(userDetails.getUsername());
        }
    }
