package com.example.jobseeker.ui.viewmodels.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jobseeker.domain.usecase.db.VacancyDao
import com.example.jobseeker.domain.usecase.db.DeleteVacancyUseCase
import com.example.jobseeker.domain.usecase.network.FetchVacanciesByParametersUseCase
import com.example.jobseeker.domain.usecase.network.FetchVacanciesBySkillsUseCase
import com.example.jobseeker.domain.usecase.db.SaveVacancyUseCase
import com.example.jobseeker.ui.viewmodels.saved_vacancies.VacancyViewModel
import com.example.jobseeker.ui.viewmodels.search_by_parameters.SearchByParametersViewModel
import com.example.jobseeker.ui.viewmodels.search_by_skills.SearchBySkillsViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val saveVacancyUseCase: SaveVacancyUseCase,
    private val deleteVacancyUseCase: DeleteVacancyUseCase,
    private val fetchVacanciesByParametersUseCase: FetchVacanciesByParametersUseCase,
    private val fetchVacanciesBySkillsUseCase: FetchVacanciesBySkillsUseCase,
    private val vacancyDao: VacancyDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VacancyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VacancyViewModel(
                vacancyDao,
                saveVacancyUseCase,
                deleteVacancyUseCase
            ) as T
        } else if (modelClass.isAssignableFrom(SearchByParametersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchByParametersViewModel(
                fetchVacanciesByParametersUseCase
            ) as T
        } else if (modelClass.isAssignableFrom(SearchBySkillsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchBySkillsViewModel(
                fetchVacanciesBySkillsUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class" + modelClass::class.simpleName)
    }
}