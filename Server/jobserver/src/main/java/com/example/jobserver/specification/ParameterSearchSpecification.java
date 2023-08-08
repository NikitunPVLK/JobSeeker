package com.example.jobserver.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class ParameterSearchSpecification extends AbstractSearchSpecification {
    private final String keyWords;
    private final String experience;
    private final String category;
    private final String location;

    public ParameterSearchSpecification(String keyWords, String experience, String category, String location) {
        this.keyWords = keyWords;
        this.experience = experience;
        this.category = category;
        this.location = location;
    }

    @Override
    protected Predicate buildPredicate(Root root, CriteriaBuilder criteriaBuilder) {
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
            String searchTerm = "%" + location.toLowerCase() + "%";
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("location")), searchTerm));
        }

        return predicate;
    }
}
