package com.firstProjectDemo.first_api.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtServiceLibrary {

    private final SecretKey secretKey;

    public JwtServiceLibrary(@Value("${jwt.secret}") String base64Secret) {
        byte [] bytes= Decoders.BASE64.decode(base64Secret);
        this.secretKey = Keys.hmacShaKeyFor(bytes);
    }
    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()*1000*30*60))
                .signWith(secretKey)
                .compact();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Claims extractClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token){
        Claims claims=extractClaims(token);
        return claims.getSubject();
    }

    public boolean isValid(String token, UserDetails user){
        try{
           String username=extractUsername(token);
           return (user.getUsername().equals(username) && !isTokenExpired(token));
        }catch (Exception e){
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Claims claims=extractClaims(token);
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }
}
