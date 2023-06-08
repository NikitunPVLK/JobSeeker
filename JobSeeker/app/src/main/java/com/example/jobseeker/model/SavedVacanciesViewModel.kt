package com.example.jobseeker.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobseeker.data.VacancyDao
import kotlinx.coroutines.launch


class SavedVacanciesViewModel(private val vacancyDao: VacancyDao) : ViewModel() {

    val vacancies: LiveData<List<Vacancy>> = vacancyDao.getVacancies()

    fun addVacancy(vacancy: Vacancy) {
        viewModelScope.launch {
            vacancyDao.insert(vacancy)
        }
    }

    fun deleteVacancy(vacancy: Vacancy) {
        viewModelScope.launch {
            vacancyDao.delete(vacancy)
        }
    }
}

