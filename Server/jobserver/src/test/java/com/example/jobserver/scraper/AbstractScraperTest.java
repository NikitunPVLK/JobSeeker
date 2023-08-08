package com.example.jobserver.scraper;

import com.example.jobserver.model.Vacancy;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AbstractScraperTest {

    private static class TestAbstractScraper extends AbstractScraper {

        TestAbstractScraper() {
            currentVacancy = new Element("div");
            urlWithCriteria = "https://jobs.dou.ua/vacancies/";
        }

        public void setCurrentVacancy() {
            currentVacancy = new Element("div");
        }

        @Override
        public List<Vacancy> scrape() {
            return super.scrape();
        }

        @Override
        protected void buildUrlToScrape() {

        }

        @Override
        protected int getPages(Document document) {
            // Implement test logic for getPages method
            return 1;
        }

        @Override
        protected String buildPageUrl(int index) {
            // Implement test logic for buildPageUrl method
            return "super.buildPageUrl(index)";
        }

        @Override
        protected Elements getVacancyElements(Document document) {
            // Implement test logic for getVacancyElements method
            return new Elements();
        }

        @Override
        protected String getVacancyUrl() {
            return "super.getVacancyUrl()";
        }

        @Override
        protected String getTitle() {
            return "super.getTitle()";
        }

        @Override
        protected String getCompany() {
            return "";
        }

        @Override
        protected String getSalary() {
            return "super.getSalary()";
        }

        @Override
        protected String getLocation() {
            return "super.getLocation()";
        }

        @Override
        protected String getDescription(String url) {
            return "super.getDescription(url)";
        }

        @Override
        protected String getCategory() {
            return "super.getCategory()";
        }
    }

    static TestAbstractScraper testAbstractScraper;
    static Document mockDoc;

    @BeforeAll
    public static void before() {
        testAbstractScraper = new TestAbstractScraper();
        mockDoc = mock(Document.class);
    }

    @Test
    void scrape() {
        testAbstractScraper.scrape();
    }

    @Test
    void buildUrlToScrape() {
        testAbstractScraper.buildUrlToScrape();
    }

    @Test
    void getPages() {
        testAbstractScraper.getPages(mockDoc);
    }

    @Test
    void buildPageUrl() {
        testAbstractScraper.buildUrlToScrape();
    }

    @Test
    void getVacancyElements() {
        testAbstractScraper.getVacancyElements(mockDoc);
    }

    @Test
    void getVacancyUrl() {
        testAbstractScraper.getVacancyUrl();
    }

    @Test
    void getTitle() {
        testAbstractScraper.getTitle();
    }

    @Test
    void getCompany() {
        testAbstractScraper.getCompany();
    }

    @Test
    void getSalary() {
        testAbstractScraper.getSalary();
    }

    @Test
    void getLocation() {
        testAbstractScraper.getLocation();
    }

    @Test
    void getDescription() {
        testAbstractScraper.getDescription("");
    }

    @Test
    void getCategory() {
        testAbstractScraper.getCategory();
    }
}