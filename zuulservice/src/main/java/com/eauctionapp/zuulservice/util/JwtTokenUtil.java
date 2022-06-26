package com.eauctionapp.zuulservice.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class JwtTokenUtil implements Serializable {

    @Value("${jwt.secret}")
    private String secret;

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    public String generateToken(String email) throws IllegalArgumentException, JWTCreationException {
        return JWT.create()
                .withSubject("User Details")
                .withClaim("email", email)
                .withIssuer("EAUCTIONAPP")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveSubject(String token)throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User Details")
                .withIssuer("EAUCTIONAPP")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("email").asString();
    }

}
