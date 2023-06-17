package com.example.jobserver.scraper;

import com.example.jobserver.model.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractScraper implements IScraper {

    protected String baseUrl;
    protected String urlWithCriteria;
    protected Element currentVacancy;

    public List<Vacancy> scrape() {
        buildUrlToScrape();
        List<Vacancy> vacancies = new ArrayList<>();
        Document doc;
        try {
            doc = Jsoup.connect(urlWithCriteria).get();
            int pages = getPages(doc);
            for (int i = 1; i <= pages; i++) {
                if (i > 1) {
                    String pageUrl = buildPageUrl(i);
                    doc = Jsoup.connect(pageUrl).get();
                }
                Elements vacancyElements = getVacancyElements(doc);
                for (Element vacancy : vacancyElements) {
                    vacancies.add(processVacancy(vacancy));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return vacancies;
    }

    private Vacancy processVacancy(Element vacancy) throws IOException {
        currentVacancy = vacancy;
        String url = getVacancyUrl();
        String title = getTitle();
        String company = getCompany();
        // JobsUa ad handling
        if (company == null) {
            return null;
        }
        String salary = getSalary();
        String location = getLocation();
        String description = getDescription(url);
        String category = getCategory();
        return new Vacancy(url, title, company, salary, location, description, category);
    }

    protected abstract void buildUrlToScrape();

    protected abstract int getPages(Document document);

    protected abstract String buildPageUrl(int index);

    protected abstract Elements getVacancyElements(Document document);

    protected abstract String getVacancyUrl();

    protected abstract String getTitle();

    protected abstract String getCompany();

    protected abstract String getSalary();

    protected abstract String getLocation();

    protected abstract String getDescription(String url) throws IOException;

    protected abstract String getCategory();
}
