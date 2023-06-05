package com.example.jobserver.models;

public class Vacancy {
    public String url;
    public String title;
    public String company;
    public String salary;
    public String location;
    public String description;

    public Vacancy(String url, String title, String company, String salary, String location, String description) {
        this.url = url;
        this.title = title;
        this.company = company;
        this.salary = salary;
        this.location = location;
        this.description = description;
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
