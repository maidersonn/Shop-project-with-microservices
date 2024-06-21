package com.maider.authenticationMicroService.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Date;
import java.util.List;

import static com.maider.authenticationMicroService.security.Constants.TOKEN_EXPIRATION_TIME;
import static com.maider.authenticationMicroService.security.Constants.getSigningKey;


@Configuration
public class JWTAuthenticationConfig {
    @Value("${secret.key}")
    private String secretKey;
    public String getJWTToken(String username) {
        String token = Jwts
                .builder()
                .setId("maiderJWT")
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .signWith(getSigningKey(secretKey),  SignatureAlgorithm.HS512).compact();

        return "Bearer " + token;
    }
}
