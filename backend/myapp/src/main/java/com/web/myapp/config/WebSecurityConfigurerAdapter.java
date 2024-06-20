package com.web.myapp.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.core.env.Environment;

@Configuration
public class WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private Environment env;
 
    public WebSecurityConfigurerAdapter(Environment env) {
        this.env = env;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permitir solicitudes desde cualquier origen
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                .allowedHeaders("*"); // Encabezados permitidos
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                String origins = env.getProperty("management.endpoints.web.cors.allowed-origins");
                if ((origins != null) && (!origins.trim().equals("*"))) {
                    String[] valOrigins = Arrays.stream(origins.split(","))
                            .map(String::trim)
                            .toArray(String[]::new);
                    String methods = env.getProperty("management.endpoints.web.cors.allowed-methods");
                    registry.addMapping("/**")
                    .allowedOrigins(valOrigins)
                    .allowedMethods(methods)
                    .allowedHeaders("Authorization", "Content-type", "multipart/form-data")
                    .exposedHeaders("*")
                    .maxAge(3600);
                }
            }
        };
    }
}
