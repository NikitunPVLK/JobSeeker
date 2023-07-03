package com.example.jobserver.scraper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class WorkUaScraperTest {

    private static class TestWorkUaScraper extends WorkUaScraper {

        TestWorkUaScraper() {
            currentVacancy = new Element("a");
            companyAndLocationBlock = new Element("span");
        }

        public void setCurrentVacancy() {
            currentVacancy = new Element("div");
        }
        @Override
        protected int getPages(Document document) {
            // Implement test logic for getPages method
            return super.getPages(document);
        }

        @Override
        protected String buildPageUrl(int index) {
            // Implement test logic for buildPageUrl method
            return super.buildPageUrl(index);
        }

        @Override
        protected Elements getVacancyElements(Document document) {
            // Implement test logic for getVacancyElements method
            return super.getVacancyElements(document);
        }

        @Override
        protected String getVacancyUrl() {
            return super.getVacancyUrl();
        }

        @Override
        protected String getTitle() {
            return super.getTitle();
        }

        @Override
        protected String getCompany() {
            return "";
        }

        @Override
        protected String getSalary() {
            return super.getSalary();
        }

        @Override
        protected String getLocation() {
            return super.getLocation();
        }

        @Override
        protected String getDescription(String url) {
            try {
                return super.getDescription(url);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected String getCategory() {
            return super.getCategory();
        }
    }

    static TestWorkUaScraper workUaScraper;
    static Document mockDoc;

    @BeforeAll
    public static void before() {
        workUaScraper = new TestWorkUaScraper();
        mockDoc = mock(Document.class);
    }
    @Test
    void buildUrlToScrape() {
        workUaScraper.buildUrlToScrape();
    }

    @Test
    void getPages() {
        workUaScraper.getPages(new Document("https://www.work.ua/jobs-java/"));
    }

    @Test
    void buildPageUrl() {
        workUaScraper.buildPageUrl(1);
    }

    @Test
    void getVacancyElements() {
        workUaScraper.getVacancyElements(mockDoc);
    }

    @Test
    void getVacancyUrl() {
        workUaScraper.getVacancyUrl();
    }

    @Test
    void getTitle() {
        workUaScraper.getTitle();
    }

    @Test
    void getCompany() {
        workUaScraper.getCompany();
    }

    @Test
    void getSalary() {
//        workUaScraper.getSalary();
    }

    @Test
    void getLocation() {
//        workUaScraper.getLocation();
    }

    @Test
    void getDescription() {
        workUaScraper.getDescription("https://www.work.ua/jobs/5133092/");
    }

    @Test
    void getCategory() {
        workUaScraper.getCategory();
    }
}