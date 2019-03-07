package com.oryam.howto.application.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Import this configuration class in the @Import() annotation on bootable application class.
 */
@Configuration
public class CorsConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(CorsConfiguration.class);

    @Value("${cors.security.origins}")
    private String[] origins;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                LOG.info("Allowed request origins from {} servers {}", origins.length, origins);

                registry.addMapping("/**")
                        .allowedOrigins(origins)
                        .allowedMethods(org.springframework.web.cors.CorsConfiguration.ALL)
                        .allowedHeaders(HttpHeaders.CONTENT_TYPE,
                                        HttpHeaders.ORIGIN,
                                        HttpHeaders.AUTHORIZATION,
                                        HttpHeaders.ACCEPT,
                                        HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD,
                                        HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS,
                                        "X-Requested-With",
                                        "XMLHttpRequest",
                                        "Client-Security-Token")
                        .exposedHeaders("xsrf-token")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }

}
