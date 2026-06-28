package com.company.employee.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {


    private final String SECRET =
            "employee-ai-agent-secret-key-employee-ai-agent-secret-key";


    private final SecretKey key =
            Keys.hmacShaKeyFor(
                    SECRET.getBytes(StandardCharsets.UTF_8)
            );


    public String generateToken(String username) {


        return Jwts.builder()

                .setSubject(username)

                .setIssuedAt(new Date())

                .setExpiration(
                        new Date(
                            System.currentTimeMillis()
                            + 1000 * 60 * 60
                        )
                )

                .signWith(key, SignatureAlgorithm.HS256)

                .compact();

    }
    public String extractUsername(String token){


    return Jwts.parserBuilder()

            .setSigningKey(key)

            .build()

            .parseClaimsJws(token)

            .getBody()

            .getSubject();

}

}