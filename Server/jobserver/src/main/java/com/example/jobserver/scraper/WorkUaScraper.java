package com.example.jobserver.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkUaScraper extends AbstractScraper {
    protected Element companyAndLocationBlock;

    public WorkUaScraper() {
        baseUrl = "https://www.work.ua";
    }

    @Override
    protected void buildUrlToScrape() {
        urlWithCriteria = baseUrl + "/ru/jobs";
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
        Element vacancyHolder = document.getElementById("pjax-job-list");
        Elements vacancyElements = new Elements();
        if (vacancyHolder != null) {
            for (Element element : vacancyHolder.children()) {
                if (element.className().equals("text-muted add-bottom")) {
                    break;
                } else if (element.className().equals("card card-hover card-visited wordwrap job-link")) {
                    vacancyElements.add(element);
                }
            }
        }
        return vacancyElements;
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
            Element salaryElement = currentVacancy.getElementsByTag("div").get(1);
            List<String> textElements = new ArrayList<>();
            for (Element element : salaryElement.children()) {
                if (!element.hasClass("middot")) {
                    textElements.add(element.text());
                }
            }
            return String.join(", ", textElements);
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

    @Override
    protected String getCategory() {
        return "";
    }
}
