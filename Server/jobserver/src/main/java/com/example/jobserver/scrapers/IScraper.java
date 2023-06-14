package com.example.jobserver.scrapers;

import com.example.jobserver.models.Criteria;
import com.example.jobserver.models.Vacancy;

import java.util.List;

public interface IScraper {
    List<Vacancy> scrape();
}
