package com.example.jobserver.data;

import com.example.jobserver.model.Vacancy;
import com.example.jobserver.specification.ParameterSearchSpecification;
import com.example.jobserver.specification.SkillSearchSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class VacancyServiceTest {

    @Autowired
    private VacancyService vacancyService;

    @Autowired
    private VacancyRepository vacancyRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveVacancies() {
        // Create a list of vacancies for testing
        List<Vacancy> vacancies = new ArrayList<>();
        vacancies.add(new Vacancy());
        vacancies.add(new Vacancy());
        // Call the method under test
        vacancyService.saveVacancies(vacancies);
    }

    @Test
    public void testFindAllByCriteria() {
        // Create a sample specification for testing
        Specification<Vacancy> specification = new SkillSearchSpecification(List.of("java"));
        vacancyService.findAllByCriteria(specification);
    }

    @Test
    public void testFindAllByCriteriaDefault() {
        // Create a sample specification for testing
        Specification<Vacancy> specification = new ParameterSearchSpecification("key words", "1 year", "category", "location");
        vacancyService.findAllByCriteria(specification);
    }
}