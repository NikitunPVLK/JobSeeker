package com.example.jobserver.data;

import com.example.jobserver.model.Vacancy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacancyService {
    @Autowired
    private VacancyRepository vacancyRepository;

    public void saveVacancies(List<Vacancy> vacancies) {
        vacancyRepository.saveAll(vacancies);
    }

    public List<Vacancy> findAllByCriteria(Specification<Vacancy> specification) {
        return vacancyRepository.findAll(specification);
    }
}
