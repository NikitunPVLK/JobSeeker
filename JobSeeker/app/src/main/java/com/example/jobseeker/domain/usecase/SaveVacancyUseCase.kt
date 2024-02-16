package com.example.jobseeker.domain.usecase

import com.example.jobseeker.data.database.VacancyDao
import com.example.jobseeker.domain.Vacancy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SaveVacancyUseCase(private val vacancyDao: VacancyDao) {

    suspend fun saveVacancy(vacancy: Vacancy): Boolean {
        val existingVacancy = withContext(Dispatchers.IO) {
            vacancyDao.getVacancyByUrl(vacancy.url)
        }
        if (existingVacancy == null) {
            return withContext(Dispatchers.IO) {
                vacancyDao.insert(vacancy)
                return@withContext true
            }
        } else {
            return false
        }
    }

}