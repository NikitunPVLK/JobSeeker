package com.example.jobserver.models;

public class Criteria {
    public String search;
    public String category;
    public Experience experience;
    public String region;
    public Location location;

    public Criteria(String search, String category, Experience experience, String region, Location location) {
        this.search = search;
        this.category = category;
        this.experience = experience;
        this.region = region;
        this.location = location;
    }
}
