package com.example.jobseeker.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobseeker.model.Vacancy
import com.example.jobseeker.network.VacanciesApi
import kotlinx.coroutines.launch

class SkillBasedSearchViewModel : ViewModel() {
    private lateinit var _skills: List<String>
    val skills get() = _skills

    private var _vacancies = MutableLiveData<List<Vacancy>>()
    val vacancies: LiveData<List<Vacancy>>
        get() = _vacancies

    fun submitSkills(skills: List<String>) {
        _skills = skills
        viewModelScope.launch{
            try {
                _vacancies.value = VacanciesApi.retrofitService.getVacanciesBySkills(skills)
            } catch (e: Exception) {
                Log.e("SBSViewModel", e.message.toString())
            }
        }
    }
}