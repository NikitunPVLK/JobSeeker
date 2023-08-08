package com.example.jobserver.model;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VacancyTest {

    static Vacancy vacancy;

    @BeforeAll
    public static void beforeAll() {
        vacancy = new Vacancy();
        vacancy = new Vacancy(
                "url",
                "title",
                "company",
                "salary",
                "location",
                "description",
                "category");

    }

    @Test
    void testToString() {
        vacancy.toString();
    }

    @Test
    void getId() {
        vacancy.getId();
    }

    @Test
    void getUrl() {
        vacancy.getUrl();
    }

    @Test
    void getTitle() {
        vacancy.getTitle();
    }

    @Test
    void getCompany() {
        vacancy.getCompany();
    }

    @Test
    void getSalary() {
        vacancy.getSalary();
    }

    @Test
    void getLocation() {
        vacancy.getLocation();
    }

    @Test
    void getDescription() {
        vacancy.getDescription();
    }

    @Test
    void getCategory() {
        vacancy.getCategory();
    }

    @Test
    void setId() {
        vacancy.setId(2);
    }

    @Test
    void setUrl() {
        vacancy.setUrl("url2");
    }

    @Test
    void setTitle() {
        vacancy.setTitle("title2");
    }

    @Test
    void setCompany() {
        vacancy.setCompany("company");
    }

    @Test
    void setSalary() {
        vacancy.setSalary("salary2");
    }

    @Test
    void setLocation() {
        vacancy.setLocation("location");
    }

    @Test
    void setDescription() {
        vacancy.setDescription("description");
    }

    @Test
    void setCategory() {
        vacancy.setCategory("category");
    }

    @Test
    void testEquals() {
        vacancy.equals(vacancy);
    }

    @Test
    void canEqual() {
//        vacancy.canEqual(vacancy);
    }

    @Test
    void testHashCode() {
        vacancy.hashCode();
    }
}