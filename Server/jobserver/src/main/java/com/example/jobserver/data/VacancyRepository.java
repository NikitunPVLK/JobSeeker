package com.example.jobserver.data;

import com.example.jobserver.models.Vacancy;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Integer> {
//    List<Vacancy> findByLocation(String location);

//    List<Vacancy> findByCategory(String category);
//
//    List<Vacancy> findByExperience(String experience);
//
//    List<Vacancy> findByKeyWords(@Param("key_words") String keyWords);

    List<Vacancy> findAll(Specification<Vacancy> specification);
}
