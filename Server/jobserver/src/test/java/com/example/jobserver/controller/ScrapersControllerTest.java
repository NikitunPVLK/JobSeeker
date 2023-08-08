package com.example.jobserver.controller;

import com.example.jobserver.api_key.ApiKeyManager;
import com.example.jobserver.data.VacancyService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(ScrapersController.class)
class ScrapersControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VacancyService vacancyService;

    @Test
    public void testScrapersEndpoint() throws Exception {
        ApiKeyManager.generateApiKey();
        mockMvc.perform(MockMvcRequestBuilders.get("/scrapers")
                        .header("apiKey", "not_valid"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andDo(print()); // Use andDo(print()) to print detailed request/response information
    }
}