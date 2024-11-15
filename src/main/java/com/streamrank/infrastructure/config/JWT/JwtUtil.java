package com.streamrank.infrastructure.config.JWT;

import com.streamrank.domain.user.model.User;
import com.streamrank.domain.user.service.UserDomainService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${streamrank.jwt.secretkey}")
    private String secretKey;

    @Value("${streamrank.jwt.expirationtime}")
    private long expirationTime;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(String username, Collection<?> roles) {
        Map<String, Object> claims = new HashMap();
        if(roles != null) claims.put("roles", roles);
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Collection<?> extractRoles(String token) {
        return (Collection<?>) extractAllClaims(token).get("roles");
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public boolean isTokenValid(String token, User userDetails){
        final String username = extractUsername(token);
        return (username != null && username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenCorrect(String token, UserDomainService userDomainService) {
        try {
            String username = extractUsername(token);

            if (username == null) {
                return false;
            }

            User user = userDomainService.findByUserName(username);
            if (user == null) {
                return false;
            }

            return isTokenValid(token, user);
        } catch (Exception e) {
            // Handle exceptions and ensure false is returned
            return false;
        }
    }
}
