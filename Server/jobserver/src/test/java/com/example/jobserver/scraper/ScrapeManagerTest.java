package com.example.jobserver.scraper;

import com.example.jobserver.data.VacancyService;
import com.example.jobserver.model.Vacancy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ScrapeManagerTest {

    VacancyService vacancyServiceMock;
    List<Vacancy> vacancies;

    @Before
    public void beforeTest (){
//        DouScraper douScraperMock = mock(DouScraper.class);
//        doNothing().when(douScraperMock.scrape());
        vacancyServiceMock = mock(VacancyService.class);
        vacancies = new ArrayList<>();
        doNothing().when(vacancyServiceMock).saveVacancies(vacancies);
    }

    @Test
    void getInstanceNotNull() {
        assertNotNull(ScrapeManager.getInstance());
    }

    @Test
    void getInstanceTheSame() {
        ScrapeManager scrapeManager1 = ScrapeManager.getInstance();
        ScrapeManager scrapeManager2 = ScrapeManager.getInstance();
        assertEquals(scrapeManager1, scrapeManager2);
    }

    @Test
    void setVacancyService() {
        ScrapeManager scrapeManager1 = ScrapeManager.getInstance();
        scrapeManager1.setVacancyService(vacancyServiceMock);
        ScrapeManager scrapeManager2 = ScrapeManager.getInstance();
        assertEquals(scrapeManager1, scrapeManager2);
    }
}