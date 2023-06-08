package com.example.jobseeker.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jobseeker.data.VacancyDao

class SavedVacanciesViewModelFactory(private val vacancyDao: VacancyDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SavedVacanciesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return SavedVacanciesViewModel(vacancyDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class" + modelClass::class.simpleName)
    }
}