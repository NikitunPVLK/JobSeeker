package com.example.jobseeker.ui.viewmodels.saved_vacancies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobseeker.domain.usecase.db.VacancyDao
import com.example.jobseeker.domain.models.Vacancy
import com.example.jobseeker.domain.usecase.db.DeleteVacancyUseCase
import com.example.jobseeker.domain.usecase.db.SaveVacancyUseCase
import kotlinx.coroutines.*

class VacancyViewModel(
    vacancyDao: VacancyDao,
    private val saveVacancyUseCase: SaveVacancyUseCase,
    private val deleteVacancyUseCase: DeleteVacancyUseCase
) : ViewModel() {

    val vacancies: LiveData<List<Vacancy>> = vacancyDao.getVacancies()

    fun deleteVacancy(vacancy: Vacancy) {
        viewModelScope.launch {
            deleteVacancyUseCase.deleteVacancy(vacancy)
        }
    }

    fun changeSavedState(vacancy: Vacancy){
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

