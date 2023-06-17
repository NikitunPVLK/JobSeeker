package com.example.jobserver.controllers;

import com.example.jobserver.data.VacancyService;
import com.example.jobserver.models.Vacancy;
import com.example.jobserver.scrapers.ScrapeManager;
import com.example.jobserver.specification.ParameterSearchSpecificationImpl;
import com.example.jobserver.specification.SkillSearchSpecificationImpl;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class VacanciesController {

    @Autowired
    private VacancyService vacancyService;

    private final Gson gson = new Gson();

    @GetMapping("/vacancies")
    public String getVacancies(@RequestParam(name = "search") String search,
                               @RequestParam(name = "category") String category,
                               @RequestParam(name = "experience") String experience,
                               @RequestParam(name = "region") String region,
                               @RequestParam(name = "location") String location
    ) {
        ParameterSearchSpecificationImpl specification = new ParameterSearchSpecificationImpl(search, experience, category, location);
        List<Vacancy> vacancies = vacancyService.findAllByCriteria(specification);
//        Criteria criteria = new Criteria(search,
//                category,
//                Experience.valueOf(experience),
//                region,
//                Location.valueOf(location));
//        scrapeManager.setVacancyService(vacancyService);
//        List<Vacancy> result = scrapeManager.getVacanciesByCriteria(criteria);
        return gson.toJsonTree(vacancies).getAsJsonArray().toString();
    }

    @GetMapping("/skills")
    public String getVacanciesBySkills(@RequestParam(name = "skill") List<String> skills) {
        NlpModelRunner nlpModelRunner = new NlpModelRunner();
        String modelResult = nlpModelRunner.getCategoryFromModel(skills);
        System.out.println(modelResult);
        List<String> categories = Arrays.asList(modelResult
                .replace("[", "")
                .replace("(", "")
                .replace("'", "")
                .replace(")", "")
                .replace("]", "")
                .split(","));
        System.out.println(categories);
        SkillSearchSpecificationImpl skillSearchSpecification = new SkillSearchSpecificationImpl(categories);

        List<Vacancy> vacancies = vacancyService.findAllByCriteria(skillSearchSpecification);
        return gson.toJsonTree(vacancies).getAsJsonArray().toString();
    }

    @GetMapping("/scrapers")
    public String runScrapers() {
        ScrapeManager scrapeManager = ScrapeManager.getInstance();
        scrapeManager.setVacancyService(vacancyService);
        scrapeManager.runScrapers();
        return "";
    }
}
