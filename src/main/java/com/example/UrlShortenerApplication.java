package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point of the URL Shortener application.
 * Starts the Spring Boot application and the embedded web server.
 */
@SpringBootApplication
public class UrlShortenerApplication {

    public static void main(String[] args) {
        // Start the Spring Boot application
        SpringApplication.run(UrlShortenerApplication.class, args);
    }
}
