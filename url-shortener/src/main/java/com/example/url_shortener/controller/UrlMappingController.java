package com.example.url_shortener.controller;


import java.io.IOException;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import com.example.url_shortener.service.UrlMappingService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/url")
public class UrlMappingController {
	
	 @Autowired
	    private UrlMappingService urlMappingService;

	    @PostMapping("/shorten")
	    public ResponseEntity<String> shortenUrl(@RequestParam String originalUrl) {
	        String shortUrl = urlMappingService.shortenUrl(originalUrl);
	        return ResponseEntity.ok(shortUrl);
	    }

	    @GetMapping("/{shortUrl}")
	    public void redirectToFullUrl(HttpServletResponse response, @PathVariable String shortUrl) throws IOException {
	        try {
	            String originalUrl = urlMappingService.getOriginalUrl(shortUrl);
	            response.sendRedirect(originalUrl);
	        } catch (NoSuchElementException e) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "URL not found", e);
	        }
	    }

	    @PostMapping("/update")
	    public ResponseEntity<Boolean> updateShortUrl(@RequestParam String shortUrl, @RequestParam String newOriginalUrl) {
	        boolean result = urlMappingService.updateShortUrl(shortUrl, newOriginalUrl);
	        return ResponseEntity.ok(result);
	    }

	    @PostMapping("/updateExpiry")
	    public ResponseEntity<Boolean> updateExpiry(@RequestParam String shortUrl, @RequestParam int daysToAdd) {
	        boolean result = urlMappingService.updateExpiry(shortUrl, daysToAdd);
	        return ResponseEntity.ok(result);
	    }

}
