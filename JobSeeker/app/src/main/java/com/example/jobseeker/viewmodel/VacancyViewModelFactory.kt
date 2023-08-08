package com.example.jobseeker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jobseeker.data.VacancyDao

class VacancyViewModelFactory(private var vacancyDao: VacancyDao? = null) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return SearchViewModel() as T
        }
        else if (modelClass.isAssignableFrom(SavedVacancyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return SavedVacancyViewModel(vacancyDao!!) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class" + modelClass::class.simpleName)
    }
}