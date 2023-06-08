package com.example.jobseeker.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class VacancyViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VacancyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return VacancyViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class" + modelClass::class.simpleName)
    }
}