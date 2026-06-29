package com.example.sp.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    //This is Base64 encoded text.
    public static final String  secret_key="243fOEFBpC2DHaRig4268XPc04PKIlGmX7b0vmq9j60HKuxwoNRIwRsSSGSEQZvjjqgsERO8kg5cv5YGzsqkei";


    public String generateToken(String email)
    {
      Map<String,Object> claims=new HashMap<>();
      return createToken(claims,email);

    }
    public String generateRefreshToken(String email)
    {
        Map<String,Object> claims=new HashMap<>();
        return createToken(claims,email);

    }
//created access token
    public String createToken(Map<String,Object> claims,String email){
        return Jwts.builder().setClaims(claims).
                setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(email)
                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 10 * 60 * 1000
                        )
                )
                .signWith(getSignKey(),SignatureAlgorithm.RS256)
                //.compact() actually creates the final JWT string.
                .compact();
    }
    //This method converts your JWT secret string into a proper cryptographic Key object that JJWT can use for signing and validating tokens.

    public String refreshToken(Map<String,Object> claims,String email){
        return Jwts.builder().setClaims(claims).
                setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(email)
                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 10 * 1000
                        )
                )
                .signWith(getSignKey(),SignatureAlgorithm.RS256)
                //.compact() actually creates the final JWT string.
                .compact();
    }
    public Key getSignKey() {
        return Keys.hmacShaKeyFor(secret_key.getBytes());
    }
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }}
