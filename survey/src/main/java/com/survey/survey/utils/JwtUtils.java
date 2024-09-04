package com.survey.survey.utils;


import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtUtils {

    @Value("${security.jwt.key.private}")
    private String privateKey;

    @Value("${security.jwt.user.generator")
    private String userGenerator;

    public String createToken(Authentication authentication){
        Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
        
        String username = authentication.getPrincipal().toString();
        String authoritues = authentication.getAuthorities()
                .stream()
                .map(r -> r.getAuthority())
                .collect(Collectors.joining(","));

        String jwtToken = JWT.create()
                .withSubject(this.userGenerator)
                .withSubject(username)
                .withClaim("authoritues", authoritues)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);
        return jwtToken;
    }

    public DecodedJWT validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer (this.userGenerator)
            .build();

            DecodedJWT jwt = verifier.verify(token);
            return jwt;
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Invalid JWT token");

        }
    }

    public String extractUsername(DecodedJWT jwt){
        return jwt.getSubject().toString();

    }

    public Claim getSpecificClaim (DecodedJWT decodedjwt, String claimName){
        return decodedjwt.getClaim(claimName);
    }

    public Map<String, Claim> getAllClaims(DecodedJWT decodedjwt){
        return decodedjwt.getClaims();
    }

}
