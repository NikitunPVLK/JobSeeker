package com.example.jobserver.scraper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DouScraperTest {
    private static class TestDouScraper extends DouScraper {

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

    @Test
    public void testProtectedMethods() {
        Document mockDocument = mock(Document.class);

        TestDouScraper scraper = new TestDouScraper();
        scraper.setCurrentVacancy();
        int pages = scraper.getPages(mockDocument);
        assertEquals(1, pages);

        assertNull(scraper.getCategory());
        assertEquals("", scraper.getCompany());
        scraper.getDescription("https://jobs.dou.ua/companies/ciklum/vacancies/238317/");
        scraper.getSalary();
        scraper.getTitle();
        scraper.getVacancyUrl();
        scraper.getVacancyElements(new Document(""));
        scraper.getLocation();
        scraper.buildPageUrl(1);
        scraper.buildUrlToScrape();
        scraper.getCompany();

    }
}