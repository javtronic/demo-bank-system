package com.rjm.sfk.bank_api.services.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * Adds CORS mappings to allow requests from the Angular client.
     * <p>
     * The mapping allows requests from http://localhost:4200 with the following
     * settings:
     * <ul>
     *     <li>Allowed HTTP methods: GET, POST, PUT, DELETE, OPTIONS</li>
     *     <li>Allowed HTTP headers: *</li>
     *     <li>Allow credentials: true</li>
     * </ul>
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
