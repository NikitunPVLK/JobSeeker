package com.example.jobseeker.viewmodel

import com.example.jobseeker.ui.viewmodels.SearchViewModel
import com.example.jobseeker.ui.viewmodels.ViewModelFactory
import org.junit.Test

class SearchViewModelFactoryTest {

    @Test
    fun create() {
        ViewModelFactory().create(SearchViewModel().javaClass)
    }
}