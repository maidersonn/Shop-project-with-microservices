package com.maider.article.config.auth;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;

public class Constants {
        public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
        public static final String TOKEN_BEARER_PREFIX = "Bearer ";
        public static final long TOKEN_EXPIRATION_TIME = 864_000_000; // 10 day

        public static Key getSigningKeyB64(String secret) {
            byte[] keyBytes = Decoders.BASE64.decode(secret);
            return Keys.hmacShaKeyFor(keyBytes);
        }

        public static Key getSigningKey(String secret) {
            byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
            return Keys.hmacShaKeyFor(keyBytes);
        }
}
