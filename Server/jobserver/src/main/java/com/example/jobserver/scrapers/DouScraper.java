package com.example.jobserver.scrapers;

import com.example.jobserver.models.Experience;
import com.example.jobserver.models.Location;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DouScraper extends AbstractScraper {

    public DouScraper() {
        baseUrl = "https://jobs.dou.ua/vacancies/";
    }

    @Override
    protected void buildUrlToScrape() {
        urlWithCriteria = baseUrl + "?";
        addLocationToUrl();
        addCategoryToUrl();
        addSearchToUrl();
        addExperienceToUrl();
        urlWithCriteria = "https://jobs.dou.ua/vacancies/?search=asdasdasdasdasd";
    }

    private void addLocationToUrl() {
        Location location = criteria.location;
        if (location != null) {
            urlWithCriteria += "city=" + switch (location) {
                case KYIV -> "Київ";
                case LVIV -> "Львів";
                case DNIPRO -> "Дніпро";
                case ODESA -> "Одеса";
                case VINNYTSYA -> "Вінниця";
                case KHARKIV -> "Харків";
                case IVANO_FRANKIVSK -> "Івано-Франківськ";
                case TERNOPIL -> "Тернопіль";
                case CHERNIVTSI -> "Чернівці";
                case LUTSK -> "Луцьк";
                case CHERKASY -> "Черкаси";
                case UZHHOROD -> "Ужгород";
                case POLTAVA -> "Полтава";
                case RIVNE -> "Рівне";
                case SUMY -> "Суми";
                case KHMELNYTSKYI -> "Хмельницький";
                case ZHYTOMYR -> "Житомир";
                case ZAPORIZHZHIA -> "Запоріжжя";
                case KREMENCHUK -> "Кременчук";
                case MYKOLAIV -> "Миколаїв";
                case CHERNIHIV -> "Чернігів";
                case DROGOBYCH -> "Дрогобич";
                case KROPYVNYTSKYI -> "Кропивницький";
                case KHERSON -> "Херсон";
                case REMOTE -> "&remote";
                case RELOCATION -> "&relocation";
                default -> "";
            };
        }
    }

    protected void addCategoryToUrl() {
        String category = criteria.category;
        if (category != null) {
            if (!category.isEmpty()) {
                urlWithCriteria += "&category=" + category;
            }
        }
    }

    private void addSearchToUrl() {
        String search = criteria.search;
        if (search != null) {
            if (!search.isEmpty()) {
                urlWithCriteria += "&search=" + search;
            }
        }
    }

    private void addExperienceToUrl() {
        Experience experience = criteria.experience;
        if (experience != null) {
            urlWithCriteria += "&exp=" + switch (experience) {
                case ONE_YEAR, TWO_YEARS -> "1-3";
                case THREE_YEARS -> "3-5";
                case FIVE_YEARS -> "5plus";
            };
        }
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
        return document.getElementsByClass("vacancy");
    }

    @Override
    protected String getVacancyUrl() {
        return currentVacancy.getElementsByClass("vt").attr("href");
    }

    @Override
    protected String getTitle() {
        return currentVacancy.getElementsByClass("vt").text();
    }

    @Override
    protected String getCompany() {
        return currentVacancy.getElementsByClass("company").text();
    }

    @Override
    protected String getSalary() {
        return currentVacancy.getElementsByClass("salary").text();
    }

    @Override
    protected String getLocation() {
        return currentVacancy.getElementsByClass("cities").text();
    }

    @Override
    protected String getDescription(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements textBlocks = doc.getElementsByClass("text b-typo vacancy-section");
        return textBlocks.toString();
    }
}
