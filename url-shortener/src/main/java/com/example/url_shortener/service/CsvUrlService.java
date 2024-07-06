package com.example.url_shortener.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.url_shortener.entity.UrlMapping;
import com.example.url_shortener.repository.UrlMappingRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@Service
public class CsvUrlService {
	
	 @Autowired
	    private UrlMappingRepository urlMappingRepository;

	    @Value("${csv.file.path}")
	    private String csvFilePath;

	  
	    public void importUrlsFromCsv(MultipartFile file) throws CsvValidationException {
	        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
	            String[] values;
	            csvReader.readNext(); // skip the header row
	            while ((values = csvReader.readNext()) != null) {
	                String originalUrl = values[0];
	                String shortUrl = generateNewShortUrl();
	                UrlMapping urlMapping = new UrlMapping(originalUrl, shortUrl);

	                boolean saved = false;
	                while (!saved) {
	                    try {
	                        urlMappingRepository.save(urlMapping);
	                        saved = true;
	                    } catch (DataIntegrityViolationException e) {
	                        // Handle the exception - generate a new short URL
	                        urlMapping.setShortUrl(generateNewShortUrl());
	                    }
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            // Handle the exception appropriately
	        }
	    }

	    private String generateNewShortUrl() {
	        // Logic to generate a new short URL
	        return UUID.randomUUID().toString().substring(0, 8);
	    }
	
		
}
