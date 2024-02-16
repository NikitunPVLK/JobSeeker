package com.example.jobseeker.domain.usecase

import com.example.jobseeker.data.database.VacancyDao
import com.example.jobseeker.domain.Vacancy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteVacancyUseCase(private val vacancyDao: VacancyDao) {

    suspend fun deleteVacancy(vacancy: Vacancy) {
        withContext(Dispatchers.IO) {
            vacancyDao.deleteByUrl(vacancy.url)
        }
    }
}