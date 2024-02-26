package com.example.jobseeker.ui.viewmodels.search_by_skills

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobseeker.data.network.common.Result
import com.example.jobseeker.domain.models.Vacancy
import com.example.jobseeker.domain.usecase.network.FetchVacanciesBySkillsUseCase
import com.example.jobseeker.ui.viewmodels.common.ISearchViewModel
import kotlinx.coroutines.launch

class SearchBySkillsViewModel(
    private val useCase: FetchVacanciesBySkillsUseCase
) : ViewModel(), ISearchViewModel {
    private val tag = "SearchBySkillsViewModel"

    private lateinit var _skills: List<String>
    val skills get() = _skills

    private var _vacancies = MutableLiveData<List<Vacancy>>()
    override val vacancies: LiveData<List<Vacancy>>
        get() = _vacancies

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