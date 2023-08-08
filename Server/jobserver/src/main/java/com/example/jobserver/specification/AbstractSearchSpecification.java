package com.example.jobserver.specification;

import com.example.jobserver.model.Vacancy;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

abstract public class AbstractSearchSpecification implements Specification<Vacancy> {
    @Override
    public Predicate toPredicate(Root<Vacancy> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return buildPredicate(root, criteriaBuilder);
    }

    abstract protected Predicate buildPredicate(Root root, CriteriaBuilder criteriaBuilder);
}
