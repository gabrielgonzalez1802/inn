package com.inn.entities.utils;

import java.security.Key;
import java.security.SignatureException;

import org.springframework.stereotype.Component;

import com.inn.entities.exceptions.JwtValidationException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    public static void validateToken(final String token) throws JwtValidationException, SignatureException {
        try {
            Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw new JwtValidationException("El token ha expirado", e);
        } catch (MalformedJwtException e) {
            throw new JwtValidationException("El token está mal formado", e);
        } catch (UnsupportedJwtException e) {
            throw new JwtValidationException("El token no es compatible", e);
        } catch (IllegalArgumentException e) {
            throw new JwtValidationException("La clave de firma está vacía o nula", e);
        } catch (JwtException e) {
            throw new JwtValidationException("Error al validar el token", e);
        }
    }

    private static Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }
}