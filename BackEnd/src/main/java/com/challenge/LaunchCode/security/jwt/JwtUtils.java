package com.challenge.LaunchCode.security.jwt;

import com.challenge.LaunchCode.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;

import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${com.challenge.LaunchCode.jwtSecret}")
    private String jwtSecret;

    @Value("${com.challenge.LaunchCode.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetailsImpl) {
            UserDetailsImpl userPrincipal = (UserDetailsImpl) principal;
            return Jwts.builder()
                    .setSubject(userPrincipal.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                    .signWith(SignatureAlgorithm.HS512, jwtSecret)
                    .compact();
        }
        throw new IllegalArgumentException("Unknown authentication type");
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .build()
                    .parseClaimsJws(authToken);

            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (JwtException e) {
            logger.error("JWT validation failed: {}", e.getMessage());
            return false;
        }
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public void setJwtInCookies(HttpServletResponse response, String jwt) {
        Cookie jwtCookie = new Cookie("JWT", jwt);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(3600);
        response.addCookie(jwtCookie);
    }

    public void clearJwtCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("JWT", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
        }