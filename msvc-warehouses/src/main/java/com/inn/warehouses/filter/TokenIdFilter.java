package com.inn.warehouses.filter;

import java.io.IOException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inn.warehouses.exceptions.JwtValidationException;
import com.inn.warehouses.utils.JwtUtil;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TokenIdFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    	
         HttpServletRequest httpRequest = (HttpServletRequest) request;
    	 String uri = httpRequest.getRequestURI();
    	
    	// Excluye swagger-ui.html
        if (uri.endsWith("/swagger-ui.html")) {
            chain.doFilter(request, response); // Permite la petición de Swagger
            return;
        }
    	
    	final ObjectMapper objectMapper = new ObjectMapper();

        String tokenId = httpRequest.getHeader("X-TOKEN-ID");

        if (tokenId != null && !tokenId.isEmpty()) {
        	
        	try {
				JwtUtil.validateToken(tokenId);
				
				// Permite la solicitud
				chain.doFilter(request, response);
			} catch (JwtValidationException | SignatureException e) {
				throw new JwtValidationException(e.getMessage(), e);
			}
        } else {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpResponse.setContentType("application/json"); // Configura el tipo de contenido

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Header X-TOKEN-ID requerido");

            httpResponse.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        }
    }

}