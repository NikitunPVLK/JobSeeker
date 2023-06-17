package com.example.jobserver.scraper;

import com.example.jobserver.data.VacancyService;
import com.example.jobserver.model.Vacancy;

import java.util.Arrays;
import java.util.List;

public class ScrapeManager {
    private VacancyService vacancyService;
    private static ScrapeManager instance;

    private ScrapeManager() {
    }

    public static ScrapeManager getInstance() {
        if (instance == null) {
            instance = new ScrapeManager();
        }
        return instance;
    }

    public void setVacancyService(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    public void runScrapers() {
        List<IScraper> scrapers = initializeScrapers();
        for (IScraper scraper : scrapers) {
            List<Vacancy> scrapeResult = scraper.scrape();
            vacancyService.saveVacancies(scrapeResult);
        }
    }

    private List<IScraper> initializeScrapers() {
        return Arrays.asList(new DouScraper());//(new DjinniScraper(), new DouScraper(), new JobsUaScraper(), new WorkUaScraper());
    }
}
