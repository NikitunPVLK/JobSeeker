package com.example.jobserver.scrapers;

import com.example.jobserver.models.Location;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WorkUaScraper extends AbstractScraper {
    private Element companyAndLocationBlock;

    public WorkUaScraper() {
        baseUrl = "https://www.work.ua";
    }

    @Override
    protected void buildUrlToScrape() {
        urlWithCriteria = baseUrl + "/ru/jobs";
        addLocationToUrl();
        addSearchToUrl();
    }

    private void addLocationToUrl() {
        Location location = criteria.location;
        if (location != null) {
            urlWithCriteria += switch (location) {
                case LVIV -> "-lviv";
                case KYIV -> "-kyiv";
                case DNIPRO -> "-dnipro";
                case ODESA -> "-odesa";
                case REMOTE -> "-remote";
                case RELOCATION -> "-other";
                default -> "";
            };
        }
    }

    private void addSearchToUrl() {
        String search = criteria.search;
        if (search != null) {
            if (!search.isEmpty()) {
                urlWithCriteria += "-" + search;
            }
        }
    }

    @Override
    protected int getPages(Document document) {
        Elements paginationElements = document.getElementsByClass("pagination hidden-xs");
        if (paginationElements.size() > 0) {
            Elements pages = paginationElements.get(0).children();
            if (pages.size() > 0) {
                Element lastPageElement = pages.get(pages.size() - 2);
                return Integer.parseInt(lastPageElement.text());
            }
        }
        return 1;
    }

    @Override
    protected String buildPageUrl(int index) {
        return urlWithCriteria + "?page=" + index;
    }

    @Override
    protected Elements getVacancyElements(Document document) {
        return document.getElementsByClass("card card-hover card-visited wordwrap job-link");
    }

    @Override
    protected String getVacancyUrl() {
        return baseUrl + currentVacancy.getElementsByTag("a").get(0).attr("href");
    }

    @Override
    protected String getTitle() {
        return currentVacancy.getElementsByTag("a").get(0).text();
    }

    @Override
    protected String getCompany() {
        companyAndLocationBlock = currentVacancy.getElementsByClass("add-top-xs").get(0);
        return companyAndLocationBlock.getElementsByTag("span").get(0).text();
    }

    @Override
    protected String getSalary() {
        if (!currentVacancy.getElementsByTag("div").get(1).hasClass("add-top-xs")) {
            return currentVacancy.getElementsByTag("div").get(1).text();
        }
        return "";
    }

    @Override
    protected String getLocation() {
        return companyAndLocationBlock
                .getElementsByTag("span")
                .stream()
                .filter(Element::hasText)
                .toList()
                .get(1)
                .text();
    }

    @Override
    protected String getDescription(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Element descriptionElement = doc.getElementById("job-description");
        return descriptionElement.toString();
    }
}
