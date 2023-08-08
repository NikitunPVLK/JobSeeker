package com.example.jobserver.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JobsUaScraper extends AbstractScraper {
    private Elements companyAndLocationBlock;

    public JobsUaScraper() {
        baseUrl = "https://jobs.ua/vacancy";
    }

    @Override
    protected void buildUrlToScrape() {
        urlWithCriteria = baseUrl;
    }

    //TODO pages
    @Override
    protected int getPages(Document document) {
        return 1;
    }

    //TODO pages
    @Override
    protected String buildPageUrl(int index) {
        return null;
    }

    @Override
    protected Elements getVacancyElements(Document document) {
        return document.getElementsByClass("b-vacancy__item js-item_list");
    }

    @Override
    protected String getVacancyUrl() {
        return currentVacancy.getElementsByClass("b-vacancy__top__title js-item_title").attr("href");
    }

    @Override
    protected String getTitle() {
        return currentVacancy.getElementsByClass("b-vacancy__top__title js-item_title").text();
    }

    @Override
    protected String getCompany() {
        companyAndLocationBlock = currentVacancy.getElementsByClass("b-vacancy__tech");
        try {
            return companyAndLocationBlock.get(0)
                    .getElementsByTag("span").get(0)
                    .text();
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    protected String getSalary() {
        return currentVacancy.getElementsByClass("b-vacancy__top__pay").text();
    }

    @Override
    protected String getLocation() {
        return companyAndLocationBlock.get(0)
                .getElementsByTag("a").text();
    }

    @Override
    protected String getDescription(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements textBlocks = doc.getElementsByClass("b-vacancy-full__block b-text js-phone-replace");
        return textBlocks.toString();
    }

    @Override
    protected String getCategory() {
        return "";
    }
}
