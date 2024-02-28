package com.example.jobseeker.ui.viewmodels.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jobseeker.domain.di.ApplicationScope
import com.example.jobseeker.domain.usecase.db.DeleteVacancyUseCase
import com.example.jobseeker.domain.usecase.db.GetSavedVacanciesUseCase
import com.example.jobseeker.domain.usecase.network.FetchVacanciesByParametersUseCase
import com.example.jobseeker.domain.usecase.network.FetchVacanciesBySkillsUseCase
import com.example.jobseeker.domain.usecase.db.SaveVacancyUseCase
import com.example.jobseeker.ui.viewmodels.saved_vacancies.VacancyViewModel
import com.example.jobseeker.ui.viewmodels.search_by_parameters.SearchByParametersViewModel
import com.example.jobseeker.ui.viewmodels.search_by_skills.SearchBySkillsViewModel
import javax.inject.Inject
import javax.inject.Provider

@ApplicationScope
class ViewModelFactory @Inject constructor(
    private val getSavedVacanciesUseCaseProvider: Provider<GetSavedVacanciesUseCase>,
    private val saveVacancyUseCaseProvider: Provider<SaveVacancyUseCase>,
    private val deleteVacancyUseCaseProvider: Provider<DeleteVacancyUseCase>,
    private val fetchVacanciesByParametersUseCaseProvider: Provider<FetchVacanciesByParametersUseCase>,
    private val fetchVacanciesBySkillsUseCaseProvider: Provider<FetchVacanciesBySkillsUseCase>,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VacancyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VacancyViewModel(
                getSavedVacanciesUseCaseProvider.get(),
                saveVacancyUseCaseProvider.get(),
                deleteVacancyUseCaseProvider.get()
            ) as T
        } else if (modelClass.isAssignableFrom(SearchByParametersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchByParametersViewModel(
                fetchVacanciesByParametersUseCaseProvider.get()
            ) as T
        } else if (modelClass.isAssignableFrom(SearchBySkillsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchBySkillsViewModel(
                fetchVacanciesBySkillsUseCaseProvider.get()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class" + modelClass::class.simpleName)
    }
}