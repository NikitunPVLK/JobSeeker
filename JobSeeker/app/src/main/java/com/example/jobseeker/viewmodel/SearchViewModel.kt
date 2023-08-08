package com.example.jobseeker.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobseeker.model.Vacancy
import com.example.jobseeker.network.VacanciesApi
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val TAG = "SearchViewModel"

    private var _keyWords: String = ""
    val keyWords: String get() = _keyWords
    private var _category: String = ""
    val category: String get() = _category
    private var _experience: String = ""
    val experience: String get() = _experience
    private var _location: String = ""
    val location: String get() = _location

    private lateinit var parametersHandler: ParametersHandler

    private lateinit var _skills: List<String>
    val skills get() = _skills

    private var _vacancies = MutableLiveData<List<Vacancy>>()
    val vacancies: LiveData<List<Vacancy>>
        get() = _vacancies

    fun searchVacanciesByParameters(
        keyWords: String,
        category: String,
        experience: String,
        location: String
    ) {
        _keyWords = keyWords
        _category = category
        _experience = experience
        _location = location
        viewModelScope.launch {
            try {
                parametersHandler = ParametersHandler()
                _vacancies.value = VacanciesApi.retrofitService.getVacanciesByParameters(
                    keyWords,
                    category,
                    parametersHandler.getFormattedExperience(experience),
                    parametersHandler.getFormattedLocation(location)
                )
            } catch (e: Exception) {
                Log.e(TAG, e.message.toString())
            }
        }
    }

    fun searchVacanciesBySkills(skills: List<String>) {
        _skills = skills
        viewModelScope.launch{
            try {
                _vacancies.value = VacanciesApi.retrofitService.getVacanciesBySkills(skills)
            } catch (e: Exception) {
                Log.e(TAG, e.message.toString())
            }
        }
    }
}