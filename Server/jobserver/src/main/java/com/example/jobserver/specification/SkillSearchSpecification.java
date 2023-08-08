package com.example.jobserver.specification;

import jakarta.persistence.criteria.*;

import java.util.List;

public class SkillSearchSpecification extends AbstractSearchSpecification {
    private final List<String> categories;

    public SkillSearchSpecification(List<String> categories) {
        this.categories = categories;
    }

    @Override
    protected Predicate buildPredicate(Root root, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();
        if (categories != null && !categories.isEmpty()) {
            Expression<String> categoryExpression = root.get("category");

            predicate = criteriaBuilder.and(predicate, categoryExpression.in(categories));
        }

        return predicate;
    }
}
