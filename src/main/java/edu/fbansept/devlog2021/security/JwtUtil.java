package edu.fbansept.devlog2021.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    public Claims getTokenBody(String token) {
        return Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    private boolean tokenNonExpire(String token) {
        return getTokenBody(token)
                .getExpiration()
                .after(new Date());
    }

    public boolean valideToken(String token, UserDetails userDetails) {
        String username = getTokenBody(token).getSubject();
        return username.equals(userDetails.getUsername()) && tokenNonExpire(token);
    }
}
