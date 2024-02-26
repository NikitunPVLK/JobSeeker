package com.example.jobseeker.domain.usecase.db

import com.example.jobseeker.domain.models.Vacancy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteVacancyUseCase(private val vacancyDao: VacancyDao) {

    suspend fun deleteVacancy(vacancy: Vacancy) {
        withContext(Dispatchers.IO) {
            vacancyDao.deleteByUrl(vacancy.url)
        }
    }
}