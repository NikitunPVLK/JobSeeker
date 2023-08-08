package com.example.jobserver.scraper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class DjinniScraperTest {

    private static class TestDjinniScraper extends DjinniScraper {

        TestDjinniScraper() {
            currentVacancy = new Element("div");
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
            return super.getCompany();
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

    static TestDjinniScraper djinniScraper;
    static Document mockDoc;

    @BeforeAll
    public static void before() {
        djinniScraper = new TestDjinniScraper();
        mockDoc = mock(Document.class);
    }
    @Test
    void buildUrlToScrape() {
        djinniScraper.buildUrlToScrape();
    }

    @Test
    void getPages() {
        djinniScraper.getPages(new Document("https://djinni.co/jobs/"));
    }

    @Test
    void buildPageUrl() {
        djinniScraper.buildPageUrl(1);
    }

    @Test
    void getVacancyElements() {
        djinniScraper.getVacancyElements(mockDoc);
    }

    @Test
    void getVacancyUrl() {
        djinniScraper.getVacancyUrl();
    }

    @Test
    void getTitle() {
        djinniScraper.getTitle();
    }

    @Test
    void getCompany() {
//        djinniScraper.getCompany();
    }

    @Test
    void getSalary() {
        djinniScraper.getSalary();
    }

    @Test
    void getLocation() {
        djinniScraper.getLocation();
    }

    @Test
    void getDescription() {
        djinniScraper.getDescription("https://djinni.co/jobs/564178-junior-android-rozrobnik/");
    }

    @Test
    void getCategory() {
        djinniScraper.getCategory();
    }
}