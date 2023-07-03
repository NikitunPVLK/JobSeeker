package com.example.jobserver.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "vacancies")
public class Vacancy {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "url",
            columnDefinition = "text")
    private String url;
    @Column(name = "title",
            columnDefinition = "text")
    private String title;
    @Column(name = "company",
            columnDefinition = "text")
    private String company;
    @Column(name = "salary",
            columnDefinition = "text")
    private String salary;
    @Column(name = "location",
            columnDefinition = "text")
    private String location;
    @Column(name = "description",
            columnDefinition = "text")
    private String description;
    @Column(name = "category")
    private String category;

    public Vacancy() {

    }

    public Vacancy(String url,
                   String title,
                   String company,
                   String salary,
                   String location,
                   String description,
                   String category) {
        this.url = url;
        this.title = title;
        this.company = company;
        this.salary = salary;
        this.location = location;
        this.description = description;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", company='" + company + '\'' +
                ", salary='" + salary + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
