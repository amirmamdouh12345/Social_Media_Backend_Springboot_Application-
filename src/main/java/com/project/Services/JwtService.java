package com.project.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtService {

    String key= "asdfcidsjfom;slfdsFSDFSDFSDDF!#@#$$!@#$!DSAFA DFSDFscfasd";             //it must be private


    public Key generateKey() {
        byte[] key = this.key.getBytes();
        return Keys.hmacShaKeyFor(key);
    }

    public String generateJWT(String username){
        return Jwts.builder()
                .signWith(generateKey())
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .compact();
    }

    public Claims getAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(generateKey())
                .build()
                .parseClaimsJws(token)      //we extract header , body,
                .getBody();
    }

    public <T> T getFromJwt(String token , Function<Claims,T> func) {
        Claims c =getAllClaims(token);
        return func.apply(c);
    }

    public String getUsernameFromToken(String token){
        return  getFromJwt(token,Claims::getSubject);
    }

    public Date getIssuedAt(String token){
        return  getFromJwt(token,Claims::getIssuedAt);
    }

    public Date getExpiration(String token){
        return  getFromJwt(token,Claims::getExpiration);
    }

    public boolean isExpired(String token){
        Date exp = getExpiration(token);
        Date now = new Date();
        return exp.before(now);
    }


}
