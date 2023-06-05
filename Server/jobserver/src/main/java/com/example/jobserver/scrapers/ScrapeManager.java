package com.example.jobserver.scrapers;

import com.example.jobserver.models.Criteria;
import com.example.jobserver.models.Vacancy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScrapeManager {
    private static ScrapeManager instance;
    private ScrapeManager() {}

    public static ScrapeManager getInstance() {
        if (instance == null) {
            instance = new ScrapeManager();
        }
        return instance;
    }

    public List<Vacancy> getVacanciesByCriteria(Criteria criteria) {
        List<IScraper> scrapers = initializeScrapers();
        List<Vacancy> vacancies = new ArrayList<>();
        for (IScraper scraper: scrapers) {
            vacancies.addAll(scraper.scrape(criteria));
        }
        return vacancies;
    }

    private List<IScraper> initializeScrapers() {
        return Arrays.asList(new DjinniScraper(), new DouScraper(), new JobsUaScraper(), new WorkUaScraper());
    }
}
