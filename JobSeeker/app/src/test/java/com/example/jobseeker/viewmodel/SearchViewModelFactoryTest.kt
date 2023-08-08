package com.example.jobseeker.viewmodel

import org.junit.Test

class SearchViewModelFactoryTest {

    @Test
    fun create() {
        VacancyViewModelFactory().create(SearchViewModel().javaClass)
    }
}