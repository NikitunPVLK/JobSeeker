package com.example.jobserver.controllers;

import com.example.jobserver.models.Criteria;
import com.example.jobserver.models.Experience;
import com.example.jobserver.models.Location;
import com.example.jobserver.models.Vacancy;
import com.example.jobserver.scrapers.ScrapeManager;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacancies")
public class VacanciesController {
    private final ScrapeManager scrapeManager = ScrapeManager.getInstance();

    @GetMapping
    public String getVacancies(@RequestParam(name = "search") String search,
                               @RequestParam(name = "category") String category,
                               @RequestParam(name = "experience") String experience,
                               @RequestParam(name = "region") String region,
                               @RequestParam(name = "location") String location
    ) {
        Criteria criteria = new Criteria(search,
                category,
                Experience.valueOf(experience),
                region,
                Location.valueOf(location));
        List<Vacancy> result = scrapeManager.getVacanciesByCriteria(criteria);
        Gson gson = new Gson();
        JsonArray jsonArray = gson.toJsonTree(result).getAsJsonArray();
        return jsonArray.toString();
    }
}
