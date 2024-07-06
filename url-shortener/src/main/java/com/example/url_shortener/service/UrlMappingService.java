package com.example.url_shortener.service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.url_shortener.entity.UrlMapping;
import com.example.url_shortener.repository.UrlMappingRepository;

@Service
public class UrlMappingService {
	 /* @Autowired
	    private UrlMappingRepository urlMappingRepository;

	    private static final String BASE_URL = "http://localhost:8080/";

	    public String shortenUrl(String originalUrl) {
	        String shortUrl = generateShortUrl();
	        LocalDateTime expiresAt = LocalDateTime.now().plusMonths(10);
	        UrlMapping urlMapping = new UrlMapping();
	        urlMapping.setOriginalUrl(originalUrl);
	        urlMapping.setShortUrl(shortUrl);
	        urlMapping.setExpiresAt(expiresAt);
	        urlMappingRepository.save(urlMapping);
	        return BASE_URL + shortUrl;
	    }

	    public String getOriginalUrl(String shortUrl) {
	        UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl)
	                .orElseThrow(() -> new NoSuchElementException("URL not found"));
	        return urlMapping.getOriginalUrl();
	    }

	    public boolean updateShortUrl(String shortUrl, String newOriginalUrl) {
	        UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl)
	                .orElseThrow(() -> new NoSuchElementException("URL not found"));
	        urlMapping.setOriginalUrl(newOriginalUrl);
	        urlMappingRepository.save(urlMapping);
	        return true;
	    }

	    public boolean updateExpiry(String shortUrl, int daysToAdd) {
	        UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl)
	                .orElseThrow(() -> new NoSuchElementException("URL not found"));
	        urlMapping.setExpiresAt(urlMapping.getExpiresAt().plusDays(daysToAdd));
	        urlMappingRepository.save(urlMapping);
	        return true;
	    }

	    private String generateShortUrl() {
	       // return RandomStringUtils.randomAlphanumeric(8); // Generate a random 8-character string
	    	String shortUrl;
	        do {
	            shortUrl = RandomStringUtils.randomAlphanumeric(8);
	        } while (urlMappingRepository.findByShortUrl(shortUrl).isPresent());
	        return shortUrl;
	    }*/ 

    @Autowired
    private UrlMappingRepository urlMappingRepository;

    @Value("${base.url}")
    private String baseUrl;

    public String shortenUrl(String originalUrl) {
        String shortUrl = generateShortUrl();
        LocalDateTime expiresAt = LocalDateTime.now().plusMonths(10);

        UrlMapping urlMapping = new UrlMapping(shortUrl, shortUrl);
        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setShortUrl(shortUrl);
        urlMapping.setExpiresAt(expiresAt);

        urlMappingRepository.save(urlMapping);

        return baseUrl + shortUrl;
    }

    public String getOriginalUrl(String shortUrl) {
        UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new NoSuchElementException("URL not found"));

        return urlMapping.getOriginalUrl();
    }

    public boolean updateShortUrl(String shortUrl, String newOriginalUrl) {
        UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new NoSuchElementException("URL not found"));

        urlMapping.setOriginalUrl(newOriginalUrl);
        urlMappingRepository.save(urlMapping);

        return true;
    }

    public boolean updateExpiry(String shortUrl, int daysToAdd) {
        UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new NoSuchElementException("URL not found"));

        urlMapping.setExpiresAt(urlMapping.getExpiresAt().plusDays(daysToAdd));
        urlMappingRepository.save(urlMapping);

        return true;
    }

    private String generateShortUrl() {
        String shortUrl;
        do {
            shortUrl = RandomStringUtils.randomAlphanumeric(8);
        } while (urlMappingRepository.findByShortUrl(shortUrl).isPresent());

        return shortUrl;
    }


}
