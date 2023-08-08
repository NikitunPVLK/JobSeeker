package com.example.jobserver.scraper;

import com.example.jobserver.model.Vacancy;

import java.util.List;

public interface IScraper {
    List<Vacancy> scrape();
}
