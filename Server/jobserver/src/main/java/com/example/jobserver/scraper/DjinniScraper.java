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
//        addSearchToUrl();
//        addExperienceToUrl();
//        addRegionToUrl();
//        addLocationToUrl();
    }

//    private void addSearchToUrl() {
//        String search = criteria.search;
//        if (search != null) {
//            if (!search.isEmpty()) {
//                urlWithCriteria += "?keywords=" + search;
//            }
//        }
//    }
//
//    private void addExperienceToUrl() {
//        Experience experience = criteria.experience;
//        if (experience != null) {
//            urlWithCriteria += "&exp_level=" + switch (experience) {
//                case ONE_YEAR -> "1y";
//                case TWO_YEARS -> "2y";
//                case THREE_YEARS -> "3y";
//                case FIVE_YEARS -> "5y";
//            };
//        }
//    }
//
//    private void addRegionToUrl() {
//        String region = criteria.region;
//        if (region != null) {
//            if (!region.isEmpty()) {
//                urlWithCriteria += "&region=" + region;
//            }
//        }
//    }
//
//    // TODO: other regions, remote
//    private void addLocationToUrl() {
//        Location location = criteria.location;
//        if (location != null) {
//            urlWithCriteria += "&location=" + switch (location) {
//                case KYIV -> "kyiv";
//                case VINNYTSYA -> "vinnytsia";
//                case DNIPRO -> "dnipro";
//                case IVANO_FRANKIVSK -> "ivano-frankivsk";
//                case ZHYTOMYR -> "zhytomyr";
//                case ZAPORIZHZHIA -> "zaporizhzhia";
//                case LVIV -> "lviv";
//                case MYKOLAIV -> "mykolaiv";
//                case ODESA -> "odesa";
//                case TERNOPIL -> "ternopil";
//                case KHARKIV -> "kharkiv";
//                case KHMELNYTSKYI -> "khmelnytskyi";
//                case CHERKASY -> "cherkasy";
//                case CHERNIHIV -> "chernihiv";
//                case CHERNIVTSI -> "chernivtsi";
//                case UZHHOROD -> "uzhhorod";
//                default -> "";
//            };
//        }
//    }

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
