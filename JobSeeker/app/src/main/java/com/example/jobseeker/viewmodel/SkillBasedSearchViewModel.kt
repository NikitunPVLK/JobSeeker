package com.example.jobseeker.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobseeker.network.VacanciesApi
import kotlinx.coroutines.launch

class SkillBasedSearchViewModel : ViewModel() {
    private lateinit var _skills: List<String>

    fun submitSkills(skills: String) {
        _skills = skills.split(" ")
        viewModelScope.launch{
            try {
                VacanciesApi.retrofitService.getVacanciesBySkills(_skills)
            } catch (e: Exception) {
                Log.e("SBSViewModel", e.message.toString())
            }
        }
    }
}