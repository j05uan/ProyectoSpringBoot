package com.survey.survey.security.config.filter;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.survey.survey.utils.JwtUtils;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenValidater extends OncePerRequestFilter{

    private JwtUtils jwtUtils = new JwtUtils();

    

    public JwtTokenValidater(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }



    @Override
    protected void doFilterInternal( @NonNull HttpServletRequest request,  @NonNull HttpServletResponse response,  @NonNull FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(jwtToken != null && jwtToken.startsWith("Bearer ")){ 
            jwtToken = jwtToken.substring(7);
            DecodedJWT decodedJWT = jwtUtils.validateToken(jwtToken);

            String username = jwtUtils.extractUsername(decodedJWT);
            String authoritues = jwtUtils.getSpecificClaim(decodedJWT, "authoritues").asString();

            // Aca quedaste JP
        }

        filterChain.doFilter(request, response);
            
    }

    

}
