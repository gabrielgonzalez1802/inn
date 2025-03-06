package com.inn.gateway.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.inn.gateway.exceptions.JwtValidationException;
import com.inn.gateway.util.JwtUtil;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

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
                	JwtUtil.validateToken(authHeader);

                    // Agregar token como header en los request
                    exchange.getRequest().mutate()
                            .header("X-TOKEN-ID", authHeader)
                            .build(); 

                } catch (JwtValidationException e) {
                    throw e;
                }catch (Exception e) {
                    throw new RuntimeException("un authorized access to application");
                }
            }
            return chain.filter(exchange);
        };
    }

    public static class Config {

    }
}