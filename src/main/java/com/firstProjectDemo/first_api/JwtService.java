package com.firstProjectDemo.first_api;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.hibernate.annotations.Audited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {



    private final SecretKey secretKey;

    public JwtService(@Value("${jwt.secret}") String base64Secret) {
        byte [] bytes=Decoders.BASE64.decode(base64Secret);
        this.secretKey=Keys.hmacShaKeyFor(bytes);
    }


    public String generateToken(String userName){
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*30*60))
                .signWith(secretKey)
                .compact();
    }
   public boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
   }

   private Claims extractClaims(String token){
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

   public boolean isTokenValid(String token, UserDetails userDetails){
        try{
            String name=extractUsername(token);
            return(name.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (Exception e) {
            return false;
        }
   }

    private boolean isTokenExpired(String token) {
        Claims claims=extractClaims(token);
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());

    }
}
