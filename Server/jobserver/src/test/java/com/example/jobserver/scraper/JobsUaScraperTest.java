package com.example.jobserver.scraper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class JobsUaScraperTest {

    private static class TestJobsUaScraper extends JobsUaScraper {

        TestJobsUaScraper() {
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

    static TestJobsUaScraper jobsUaScraper;
    static Document mockDoc;

    @BeforeAll
    public static void before() {
        jobsUaScraper = new TestJobsUaScraper();
        mockDoc = mock(Document.class);
    }
    @Test
    void buildUrlToScrape() {
        jobsUaScraper.buildUrlToScrape();
    }

    @Test
    void getPages() {
        jobsUaScraper.getPages(mockDoc);
    }

    @Test
    void buildPageUrl() {
        jobsUaScraper.buildPageUrl(1);
    }

    @Test
    void getVacancyElements() {
        jobsUaScraper.getVacancyElements(mockDoc);
    }

    @Test
    void getVacancyUrl() {
        jobsUaScraper.getVacancyUrl();
    }

    @Test
    void getTitle() {
        jobsUaScraper.getTitle();
    }

    @Test
    void getCompany() {
        jobsUaScraper.getCompany();
    }

    @Test
    void getSalary() {
        jobsUaScraper.getSalary();
    }

    @Test
    void getLocation() {
//        jobsUaScraper.getLocation();
    }

    @Test
    void getDescription() {
        jobsUaScraper.getDescription("https://djinni.co/jobs/564178-junior-android-rozrobnik/");
    }

    @Test
    void getCategory() {
        jobsUaScraper.getCategory();
    }
}