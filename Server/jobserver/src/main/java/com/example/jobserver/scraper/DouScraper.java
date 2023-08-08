package com.example.jobserver.scraper;

import com.example.jobserver.model.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DouScraper extends AbstractScraper {

    private final List<String> CATEGORIES = Arrays.asList(
            ".NET",
            "Analyst",
            "Android",
            "Animator",
            "Architect",
            "Artist",
            "Big Data",
            "Blockchain",
            "C++",
            "C-level",
            "Copywriter",
            "Data Science",
            "DBA",
            "Design",
            "DevOps",
            "Embedded",
            "Engineering Manager",
            "Erlang",
            "ERP/CRM",
            "Finance",
            "Flutter",
            "Front End",
            "Golang",
            "HR",
            "iOS/macOS",
            "Java",
            "Legal",
            "Marketing",
            "Node.js",
            "Office Manager",
            "Other",
            "PHP",
            "Product Manager",
            "Project Manager",
            "Python",
            "QA",
            "React Native",
            "Ruby",
            "Rust",
            "Sales",
            "Salesforce",
            "SAP",
            "Scala",
            "Scrum Master",
            "Security",
            "SEO",
            "Support",
            "SysAdmin",
            "Technical Writer",
            "Unity",
            "Unreal Engine"
    );

    private String currentCategory;

    public DouScraper() {
        baseUrl = "https://jobs.dou.ua/vacancies/";
    }

    @Override
    public List<Vacancy> scrape() {
        List<Vacancy> vacancies = new ArrayList<>();
        for (String category : CATEGORIES) {
            currentCategory = category;
            vacancies.addAll(super.scrape());
            System.out.println("FINISHED SCRAPING CATEGORY: " + currentCategory);
        }
        return vacancies;
    }

    @Override
    protected void buildUrlToScrape() {
        urlWithCriteria = baseUrl + "?";
        addCategoryToUrl();
    }

    protected void addCategoryToUrl() {
        urlWithCriteria += "&category=" + currentCategory;
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

    @Override
    protected String getCategory() {
        return currentCategory;
    }
}
