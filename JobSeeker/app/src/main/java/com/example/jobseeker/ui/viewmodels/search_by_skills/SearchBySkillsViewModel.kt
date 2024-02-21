package com.example.jobseeker.ui.viewmodels.search_by_skills

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.jobseeker.data.network.common.Result
import com.example.jobseeker.data.network.repositories.retrofit.VacanciesApi
import com.example.jobseeker.data.network.services.NetworkVacanciesService
import com.example.jobseeker.domain.usecase.FetchVacanciesBySkillsUseCase
import com.example.jobseeker.ui.viewmodels.common.ISearchViewModel
import com.example.jobseeker.ui.viewmodels.common.TempSearchViewModel
import kotlinx.coroutines.launch

class SearchBySkillsViewModel() : TempSearchViewModel(), ISearchViewModel {
    private val tag = "SearchBySkillsViewModel"

    private lateinit var _skills: List<String>
    val skills get() = _skills

    private val vacanciesService = NetworkVacanciesService()
    private val useCase =
        FetchVacanciesBySkillsUseCase(vacanciesService.getNetworkVacanciesRepository())

    fun searchVacanciesBySkills(skills: List<String>) {
        _skills = skills
        viewModelScope.launch {
            when (val result = useCase.fetchVacanciesBySkills(skills)) {
                is Result.Success -> _vacancies.value = result.vacancies
                is Result.Failure -> {
                    Log.e(tag, "FetchVacanciesByParametersUseCase failure: ${result.e.message}")
                    _vacancies.value = listOf()
                }
            }
        }
    }
}