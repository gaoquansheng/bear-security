package com.bear.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

/**
 * jwt工具类
 */
public class JwtUtil {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String createAccessToken() {

        return null;
    }

    public static String createRefreshToken() {
        return null;
    }

    public static String generateJwt(String username) {

        return Jwts.builder()
                .setSubject(username)
                .signWith(key)
                .compact();
    }

    public static Claims parseJwt(String token) {
        return Jwts.parserBuilder().setSigningKey(key)
                .build()
                .parseClaimsJws(token).getBody();
    }


}
