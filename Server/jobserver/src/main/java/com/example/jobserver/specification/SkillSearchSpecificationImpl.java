package com.example.jobserver.specification;

import com.example.jobserver.models.Vacancy;
import jakarta.persistence.criteria.*;

import java.util.List;

public class SkillSearchSpecificationImpl implements SkillSearchSpecification<Vacancy> {
    private final List<String> categories;

    public SkillSearchSpecificationImpl(List<String> categories) {
        this.categories = categories;
    }

    @Override
    public Predicate toPredicate(Root<Vacancy> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();
        if (categories != null && !categories.isEmpty()) {
            Expression<String> categoryExpression = root.get("category");

            predicate = criteriaBuilder.and(predicate, categoryExpression.in(categories));
        }

        return predicate;
    }
}
