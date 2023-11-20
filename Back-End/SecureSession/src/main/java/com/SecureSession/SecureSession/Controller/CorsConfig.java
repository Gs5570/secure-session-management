package com.SecureSession.SecureSession.Controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // This allows CORS requests to any URL path
                .allowedOrigins("http://localhost:5173")  // Replace with the URL of your React app
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // HTTP methods to allow
                .allowCredentials(true)  // If true, cookies or HTTP authentication will be included in the request
                .maxAge(3600);  // How long the browser should cache the CORS configuration (in seconds)
    }
}
