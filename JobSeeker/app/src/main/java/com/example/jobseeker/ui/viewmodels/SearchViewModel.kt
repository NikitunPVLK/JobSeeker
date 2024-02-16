package com.example.jobseeker.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobseeker.data.network.Result
import com.example.jobseeker.domain.SearchParameters
import com.example.jobseeker.domain.Vacancy
import com.example.jobseeker.domain.usecase.FetchVacanciesByParametersUseCase
import com.example.jobseeker.domain.usecase.FetchVacanciesBySkillsUseCase
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val tag = "SearchViewModel"

    private lateinit var _searchParameters: SearchParameters
    val searchParameters: SearchParameters
        get() = _searchParameters

    private lateinit var _skills: List<String>
    val skills get() = _skills

    private var _vacancies = MutableLiveData<List<Vacancy>>()
    val vacancies: LiveData<List<Vacancy>>
        get() = _vacancies

    fun searchVacanciesByParameters(searchParameters: SearchParameters) {

        val useCase = FetchVacanciesByParametersUseCase()
        viewModelScope.launch {
            when (val result = useCase.fetchVacanciesByParameters(searchParameters)) {
                is Result.Success -> _vacancies.value = result.vacancies
                is Result.Failure -> {
                    Log.e(tag, "FetchVacanciesByParametersUseCase failure")
                    _vacancies.value = listOf()
                }
            }
        }
    }

    fun searchVacanciesBySkills(skills: List<String>) {
        _skills = skills
        val useCase = FetchVacanciesBySkillsUseCase()
        viewModelScope.launch {
            when (val result = useCase.fetchVacanciesBySkills(skills)) {
                is Result.Success -> _vacancies.value = result.vacancies
                is Result.Failure -> {
                    Log.e(tag, "FetchVacanciesBySkillsUseCase failure")
                    _vacancies.value = listOf()
                }
            }
        }
    }
}