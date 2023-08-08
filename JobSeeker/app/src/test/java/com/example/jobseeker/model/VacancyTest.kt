package com.example.jobseeker.model

import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class VacancyTest {

    private lateinit var vacancy: Vacancy

    @Before
    fun before() {
        vacancy = Vacancy(
            1,
            "url",
            "title",
            "company",
            "salary",
            "location",
            "description",
            false
        )
    }

    @Test
    fun getId() {
        vacancy.id
    }

    @Test
    fun getUrl() {
        vacancy.url
    }

    @Test
    fun getTitle() {
        vacancy.title
    }

    @Test
    fun getCompany() {
        vacancy.company
    }

    @Test
    fun getSalary() {
        vacancy.salary
    }

    @Test
    fun getLocation() {
        vacancy.location
    }

    @Test
    fun getDescription() {
        vacancy.description
    }

    @Test
    fun isSaved() {
        vacancy.isSaved
    }

    @Test
    fun setSaved() {
        vacancy.isSaved = true
    }

    @Test
    fun testToString() {
        vacancy.toString()
    }

    @Test
    fun testHashCode() {
        vacancy.hashCode()
    }

    @Test
    fun testEquals() {
        vacancy.equals(vacancy)
    }
}