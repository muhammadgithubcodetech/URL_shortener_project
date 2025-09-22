package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlShortenerController {

    private static final Logger log = LoggerFactory.getLogger(UrlShortenerController.class);
    private final UrlShortenerService urlShortenerService = new UrlShortenerService();

    // DTO class for incoming JSON request
    public static class UrlRequest {
        private String longUrl;

        public String getLongUrl() {
            return longUrl;
        }

        public void setLongUrl(String longUrl) {
            this.longUrl = longUrl;
        }
    }

    @PostMapping("/api/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody UrlRequest request) {
        String longUrl = request.getLongUrl();
        log.info("Received request to shorten URL: {}", longUrl);
        String shortUrl = urlShortenerService.shortenUrl(longUrl);
        log.info("Generated short URL: {}", shortUrl);
        return ResponseEntity.ok(shortUrl);
    }

    //  CHANGED MAPPING TO /r/{shortCode}
    @GetMapping("/r/{shortCode}")
    public ResponseEntity<?> redirect(@PathVariable String shortCode) {
        log.info("Received redirect request for code: {}", shortCode);

        String longUrl = urlShortenerService.getLongUrl(shortCode);

        if (longUrl == null) {
            log.warn("No URL found for short code: {}", shortCode);
            return ResponseEntity.notFound().build();
        }

        log.info("Redirecting to: {}", longUrl);
        return ResponseEntity.status(302).header("Location", longUrl).build();
    }
}
