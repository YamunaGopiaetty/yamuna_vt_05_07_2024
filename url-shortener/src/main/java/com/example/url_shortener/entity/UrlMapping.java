package com.example.url_shortener.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "url_mapping", uniqueConstraints = @UniqueConstraint(columnNames = "short_url"))
public class UrlMapping {
	  
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "original_url", nullable = false)
	    private String originalUrl;

	    @Column(name = "short_url", nullable = false, unique = true, length = 30)
	    private String shortUrl;

	    @Column(name = "created_at", nullable = false)
	    private LocalDateTime createdAt = LocalDateTime.now();

	    @Column(name = "expires_at")
	    private LocalDateTime expiresAt;

	    public UrlMapping(String originalUrl, String shortUrl) {
	        this.originalUrl = originalUrl;
	        this.shortUrl = shortUrl;
	    }
		// Getters and Setters
	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }
	    public String getOriginalUrl() { return originalUrl; }
	    public void setOriginalUrl(String originalUrl) { this.originalUrl = originalUrl; }
	    public String getShortUrl() { return shortUrl; }
	    public void setShortUrl(String shortUrl) { this.shortUrl = shortUrl; }
	    public LocalDateTime getCreatedAt() { return createdAt; }
	    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
	    public LocalDateTime getExpiresAt() { return expiresAt; }
	    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }
	
	}
	
