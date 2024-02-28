package com.example.jobseeker.domain.usecase.db

import androidx.lifecycle.LiveData
import com.example.jobseeker.domain.models.Vacancy

class GetSavedVacanciesUseCase(private val vacancyDao: VacancyDao) {
    fun getAllVacancies(): LiveData<List<Vacancy>> {
        return vacancyDao.getVacancies()
    }
}