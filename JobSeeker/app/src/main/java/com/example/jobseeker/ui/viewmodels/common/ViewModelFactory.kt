package com.example.jobseeker.ui.viewmodels.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jobseeker.data.database.VacancyDao
import com.example.jobseeker.ui.viewmodels.saved_vacancies.VacancyViewModel
import com.example.jobseeker.ui.viewmodels.search_by_parameters.SearchByParametersViewModel
import com.example.jobseeker.ui.viewmodels.search_by_skills.SearchBySkillsViewModel

class ViewModelFactory(private var vacancyDao: VacancyDao? = null) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VacancyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VacancyViewModel(vacancyDao!!) as T
        } else if (modelClass.isAssignableFrom(SearchByParametersViewModel::class.java)) {
            @Suppress ("UNCHECKED_CAST")
            return SearchByParametersViewModel() as T
        } else if (modelClass.isAssignableFrom(SearchBySkillsViewModel::class.java)) {
            @Suppress ("UNCHECKED_CAST")
            return SearchBySkillsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class" + modelClass::class.simpleName)
    }
}