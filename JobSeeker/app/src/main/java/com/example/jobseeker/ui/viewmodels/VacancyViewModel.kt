package com.example.jobseeker.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobseeker.data.database.VacancyDao
import com.example.jobseeker.domain.Vacancy
import com.example.jobseeker.domain.usecase.DeleteVacancyUseCase
import com.example.jobseeker.domain.usecase.SaveVacancyUseCase
import kotlinx.coroutines.*


class VacancyViewModel(private val vacancyDao: VacancyDao) : ViewModel() {

    private val saveVacancyUseCase = SaveVacancyUseCase(vacancyDao)
    private val deleteVacancyUseCase = DeleteVacancyUseCase(vacancyDao)

    val vacancies: LiveData<List<Vacancy>> = vacancyDao.getVacancies()

    fun deleteVacancy(vacancy: Vacancy) {
        viewModelScope.launch {
            deleteVacancyUseCase.deleteVacancy(vacancy)
        }
    }

    fun saveVacancy(vacancy: Vacancy){
        if (vacancy.isSaved) {
            vacancy.isSaved = false
            deleteVacancy(vacancy)
        } else {
            vacancy.isSaved = true
            viewModelScope.launch {
                saveVacancyUseCase.saveVacancy(vacancy)
            }
        }
    }
}

