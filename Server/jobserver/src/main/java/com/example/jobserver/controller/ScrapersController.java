package com.example.jobserver.controller;

import com.example.jobserver.api_key.ApiKeyManager;
import com.example.jobserver.data.VacancyService;
import com.example.jobserver.scraper.ScrapeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScrapersController {
    @Autowired
    private VacancyService vacancyService;

    @GetMapping("/scrapers")
    public ResponseEntity<String> runScrapers(@RequestHeader("apiKey") String apiKey) {
        if (isValidApiKey(apiKey)) {
            ScrapeManager scrapeManager = ScrapeManager.getInstance();
            scrapeManager.setVacancyService(vacancyService);
            scrapeManager.runScrapers();
            return ResponseEntity.ok("Database updated successfully");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API key");
    }

    private boolean isValidApiKey(String apiKey) {
        return ApiKeyManager.isValidApiKey(apiKey);
    }
}
