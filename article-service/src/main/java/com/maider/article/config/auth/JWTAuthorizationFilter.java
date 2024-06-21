package com.maider.article.config.auth;

import com.maider.article.domain.entities.User;
import com.maider.article.domain.services.UserService;
import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import static com.maider.article.config.auth.Constants.*;

@Component
public class JWTAuthorizationFilter  extends OncePerRequestFilter {
    @Value(value = "${secret.key}")
    private String secretKey;
    @Autowired
    UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if (isJWTValid(request, response)) {
                Claims claims = setSigningKey(request);
                User user = userService.getUser(claims.getSubject());
                if(user == null) throw new UsernameNotFoundException("User not found");
                setAuthentication(user);
            } else {
                SecurityContextHolder.clearContext();
            }
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | UsernameNotFoundException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
    }
    private Claims setSigningKey(HttpServletRequest request) {
        String jwtToken = request.
                getHeader(HEADER_AUTHORIZACION_KEY).
                replace(TOKEN_BEARER_PREFIX, "");

        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(secretKey))
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    private void setAuthentication(User user) {

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(user.getName(), null,
                        Arrays.stream(user.getRoles()).map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private boolean isJWTValid(HttpServletRequest request, HttpServletResponse res) {
        String authenticationHeader = request.getHeader(HEADER_AUTHORIZACION_KEY);
        if (authenticationHeader == null || !authenticationHeader.startsWith(TOKEN_BEARER_PREFIX))
            return false;
        return true;
    }
}
