package com.example.jobseeker.viewmodel

import com.example.jobseeker.application.JobSeekerApplication
import org.junit.Assert.*

import org.junit.Test

class VacancyViewModelFactoryTest {

    @Test
    fun create() {
        VacancyViewModelFactory().create(VacancyViewModel().javaClass)
        VacancyViewModelFactory().create(SkillBasedSearchViewModel().javaClass)
    }
}