package com.SecureSession.SecureSession.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;


@Service
public class JwtService {
    private static final String SECRET_KEY="645905dc2a5d67863b6f05f485b56cbbff3f7193cbd5d830dd07f2e626c248e8";
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    public String extractUsername(String token) {
        log.debug("Extracting username from token");
        String username= extractClaim(token,Claims::getSubject);
        log.debug("Extracted username from token: {}", username);
        return username;
    }
    public String generateToken(Map<String,Object> extraClaims, UserDetails userDetails){
        log.debug("Generating token for username: {}", userDetails.getUsername());
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis()+(10000*60*24))).signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
    }
    public String generateToken(UserDetails userDetails) {
        log.debug("Generating token for username: {}", userDetails.getUsername());
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
        return generateToken(claims, userDetails);
    }
    public <T> T extractClaim(String token, Function<Claims,T> claimsFunction){
        final Claims claims=extractAllClaims(token);
        return claimsFunction.apply(claims);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username= extractUsername(token);
        return (username.equals(userDetails.getUsername())&&!isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Key getSignInKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
