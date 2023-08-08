package com.example.jobserver.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DjinniScraper extends AbstractScraper {
    public DjinniScraper() {
        baseUrl = "https://djinni.co";
    }

    @Override
    protected void buildUrlToScrape() {
        urlWithCriteria = baseUrl + "/jobs/";
    }

    @Override
    protected int getPages(Document document) {
        Elements pages = document.getElementsByClass("page-link");
        return pages.isEmpty()
                ? 1
                : Integer.parseInt(pages.get(pages.size() - 1 - 1).text());
    }

    @Override
    protected String buildPageUrl(int index) {
        return urlWithCriteria + "&page=" + index;
    }

    @Override
    protected Elements getVacancyElements(Document document) {
        return document.getElementsByClass("list-jobs__item list__item");
    }

    @Override
    protected String getVacancyUrl() {
        return baseUrl + currentVacancy.getElementsByClass("profile").attr("href");
    }

    @Override
    protected String getTitle() {
        return currentVacancy.getElementsByClass("profile").text();
    }

    @Override
    protected String getCompany() {
        return currentVacancy
                .getElementsByClass("list-jobs__details__info")
                .get(0)
                .getElementsByTag("a")
                .get(0)
                .text();
    }

    @Override
    protected String getSalary() {
        return currentVacancy.getElementsByClass("public-salary-item").text();
    }

    @Override
    protected String getLocation() {
        return currentVacancy.getElementsByClass("location-text").text();
    }

    @Override
    protected String getDescription(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements textBlocks = doc.getElementsByClass("profile-page-section");
        textBlocks.remove(textBlocks.size() - 1);
        return textBlocks.toString();
    }

    @Override
    protected String getCategory() {
        return "";
    }
}
