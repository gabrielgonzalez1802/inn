package com.inn.warehouses.utils;

import java.util.Arrays;
import java.util.List;

import com.inn.warehouses.exceptions.RoleAuthorizationException;

public class RoleValidator {

    public static void validateRoles(String rolesFromHeaders, String... requiredRoles) throws RoleAuthorizationException {
        if (rolesFromHeaders != null) {
            List<String> roles = Arrays.asList(rolesFromHeaders.split(","));
            // Verifica si al menos uno de los roles requeridos está presente
            for (String requiredRole : requiredRoles) {
                if (roles.contains(requiredRole)) {
                    return; // Permite el acceso
                }
            }
            throw new RoleAuthorizationException("Acceso denegado: Rol insuficiente");
        } else {
            throw new RoleAuthorizationException("Acceso denegado: Roles no encontrados");
        }
    }
}