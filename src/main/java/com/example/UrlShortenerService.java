package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Random;


public class UrlShortenerService {
    private static final Logger logger = LoggerFactory.getLogger(UrlShortenerService.class);
    private final HashMap<String, String> shortToLongMap = new HashMap<>(); // Maps short codes to long URLs
    private final HashMap<String, String> longToShortMap = new HashMap<>(); // Maps long URLs to short codes
    public final String BASE_URL = "http://localhost:8080/r/"; // <-- NOTE the /r/ here!
    private final Random random = new Random();

    public String shortenUrl(String longUrl) {
        // Clean the input URL
        longUrl = longUrl.trim();

        // If URL doesn't start with http:// or https://, add https://
        if (!longUrl.startsWith("http://") && !longUrl.startsWith("https://")) {
            longUrl = "https://" + longUrl;
        }

        // Check if URL is already shortened
        if (longToShortMap.containsKey(longUrl)) {
            String existingCode = longToShortMap.get(longUrl);
            logger.info("Returning existing short URL for: {}", longUrl);
            return BASE_URL + existingCode;
        }

        // Generate new short code
        String shortCode;
        do {
            shortCode = generateShortCode();
        } while (shortToLongMap.containsKey(shortCode));

        // Store mappings
        shortToLongMap.put(shortCode, longUrl);
        longToShortMap.put(longUrl, shortCode);

        logger.info("Created new mapping - Short code: {}, Long URL: {}", shortCode, longUrl);
        return BASE_URL + shortCode;
    }

    public String getLongUrl(String shortCode) {
        if (shortCode == null) {
            return null;
        }

        // Clean the shortCode (remove any base URL if present)
        shortCode = shortCode.replace(BASE_URL, "");

        logger.info("Looking up URL for short code: {}", shortCode);
        return shortToLongMap.get(shortCode);
    }

    private String generateShortCode() {
        StringBuilder shortCode = new StringBuilder();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < 6; i++) {
            shortCode.append(characters.charAt(random.nextInt(characters.length())));
        }
        return shortCode.toString();
    }
}
