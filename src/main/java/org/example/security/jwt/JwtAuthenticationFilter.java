package org.example.security.jwt;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import reactor.core.publisher.Mono;

import java.util.List;

public class JwtAuthenticationFilter {

    public static AuthenticationWebFilter create() {

        // ✅ AuthenticationManager explícito (evita ambigüedad)
        ReactiveAuthenticationManager authManager =authentication -> Mono.just(authentication);

        AuthenticationWebFilter filter = new AuthenticationWebFilter(authManager);

        filter.setServerAuthenticationConverter(tokenConverter());

        return filter;
    }

    private static ServerAuthenticationConverter tokenConverter() {

        return exchange -> {

            String authHeader = exchange.getRequest()
                    .getHeaders()
                    .getFirst(HttpHeaders.AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return Mono.empty();
            }

            String token = authHeader.substring(7);

            try {
                String username = JwtUtil.validateAndGetUsername(token);

                Authentication auth =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                List.of()
                        );

                return Mono.just(auth);

            } catch (Exception e) {
                return Mono.empty();
            }
        };
    }
}

