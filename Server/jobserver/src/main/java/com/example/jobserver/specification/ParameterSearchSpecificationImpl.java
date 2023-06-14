package com.example.jobserver.specification;

import com.example.jobserver.models.Vacancy;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ParameterSearchSpecificationImpl implements ParameterSearchSpecification<Vacancy> {
    private String keyWords;
    private String experience;
    private String category;
    private String location;

    public ParameterSearchSpecificationImpl(String keyWords, String experience, String category, String location) {
        this.keyWords = keyWords;
        this.experience = experience;
        this.category = category;
        this.location = location;
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();

        if (!keyWords.isEmpty()) {
            String searchTerm = "%" + keyWords.toLowerCase() + "%";
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), searchTerm));
        }

//        if (!experience.isEmpty()) {
//            predicate = criteriaBuilder.and(predicate, criteriaBuilder.)
//        }
        if (!category.isEmpty()) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("category"), category));
        }

        if (!location.isEmpty()) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("location"), location));
        }

        return predicate;
    }
}
