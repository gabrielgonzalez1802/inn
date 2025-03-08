package com.inn.gateway.filters;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<Map.Entry<String, String>> openApiEndpoints = List.of(
            Map.entry("/api/users/auth/register", "POST"),
            Map.entry("/api/users/auth/token", "POST"),
            Map.entry("/api/users/eureka", "GET"),
            Map.entry("/api/products", "GET"),
            Map.entry("/api/products/categories", "GET"),
            Map.entry("/api/addresses/states", "GET"),
            Map.entry("/api/addresses/cities", "GET")
    );

    public Predicate<ServerHttpRequest> isSecured = request ->
            openApiEndpoints.stream()
                    .noneMatch(endpoint ->
                            request.getURI().getPath().equals(endpoint.getKey()) &&
                                    request.getMethod().toString().equals(endpoint.getValue())
                    );
}