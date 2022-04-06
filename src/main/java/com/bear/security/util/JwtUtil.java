package com.bear.security.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import java.security.Key;

/**
 * jwt工具类
 */
public class JwtUtil {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateJwt(Authentication authentication) {

        return Jwts.builder()
                .setSubject(authentication.getName())
                .signWith(key)
                .compact();
    }


}
