package com.example.jobserver.data;

import com.example.jobserver.model.Vacancy;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Integer> {
    List<Vacancy> findAll(Specification<Vacancy> specification);
}
