package com.inn.gateway.filters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.inn.gateway.exceptions.JwtValidationException;
import com.inn.gateway.util.JwtUtil;

import io.jsonwebtoken.Claims;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
    	return (exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                // Header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }

                try {
                    jwtUtil.validateToken(authHeader);

                    // Extraer roles del JWT
                    Claims claims = jwtUtil.extractAllClaims(authHeader);
                    List<String> roles = ((List<Map<String, Object>>) claims.get("roles")).stream()
                            .map(role -> (String) role.get("name")) // Extrae el valor de "name"
                            .map(String::trim) // Elimina espacios en blanco
                            .collect(Collectors.toList());

                    // Agregar roles como header
                    exchange.getRequest().mutate()
                            .header("X-User-Roles", String.join(",", roles))
                            .build();

                } catch (JwtValidationException e) {
                    throw e;
                }catch (Exception e) {
                    System.out.println("invalid access...!");
                    throw new RuntimeException("un authorized access to application");
                }
            }
            return chain.filter(exchange);
        };
    }

    public static class Config {

    }
}