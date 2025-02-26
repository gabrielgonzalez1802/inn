package com.inn.entities.config;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.inn.entities.exceptions.RoleAuthorizationException;
import com.inn.entities.utils.JwtUtil;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class RoleValidationAspect {

    @Around("@annotation(RequiresRoles)")
    public Object validateRoles(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        RequiresRoles requiresRoles = signature.getMethod().getAnnotation(RequiresRoles.class);
        String[] requiredRoles = requiresRoles.value();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("X-TOKEN-ID");
        
     // Extraer roles del JWT
        Claims claims = JwtUtil.extractAllClaims(token);
        List<String> roles = ((List<Map<String, Object>>) claims.get("roles")).stream()
                .map(role -> (String) role.get("name")) // Extrae el valor de "name"
                .map(String::trim) // Elimina espacios en blanco
                .collect(Collectors.toList());

        for (String requiredRole : requiredRoles) {
            if (roles.contains(requiredRole)) {
                return joinPoint.proceed();
            }
        }

        throw new RoleAuthorizationException("Acceso denegado: Rol insuficiente");
    }
}