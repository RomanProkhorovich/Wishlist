package com.example.wishlist.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtTokenUtils {

    @Value("${jwt.lifetime}")
    private Duration lifetime;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(UserDetails details) {

        List<String> roles = details.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();


        Date created = new Date();
        Date expired = new Date(created.getTime() + lifetime.toMillis());
        return Jwts.builder()
                .claim("roles",roles)
                .setIssuedAt(created)
                .setExpiration(expired)
                .setSubject(details.getUsername())
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    public List<String> getRolesFromToken(String token){
        return getClaimsFromToken(token).get("roles",List.class);
    }

    public String getUsernameFromToken(String token){
        return getClaimsFromToken(token).getSubject();
    }

    private Claims getClaimsFromToken(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJwt(token)
                .getBody();
    }
}
