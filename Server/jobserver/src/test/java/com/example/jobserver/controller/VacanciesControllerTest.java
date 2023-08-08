package com.example.jobserver.controller;

import com.example.jobserver.api_key.ApiKeyManager;
import com.example.jobserver.data.VacancyService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(VacanciesController.class)
class VacanciesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VacancyService vacancyService;

    @Test
    void getVacancies() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vacancies")
                        .param("search", "your-search-value")
                        .param("category", "your-category-value")
                        .param("experience", "your-experience-value")
                        .param("region", "your-region-value")
                        .param("location", "your-location-value"))
                .andDo(print()); // Use andDo(print()) to print detailed request/response information
    }

    @Test
    void getVacanciesBySkills() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/skills")
                        .param("skill", "skill1", "skill2", "skill3"))
                .andDo(print()); // Use andDo(print()) to print detailed request/response information
    }
}