//package com.inn.products.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/api/**") // Rutas a las que se aplica CORS
//            .allowedOrigins("http://localhost:3000") // Orígenes permitidos
//            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
//            .allowedHeaders("*") // Cabeceras permitidas
//            .allowCredentials(true) // Si se usan credenciales (cookies, etc.)
//            .maxAge(3600); // Tiempo de caché de la respuesta preflight
//    }
//}