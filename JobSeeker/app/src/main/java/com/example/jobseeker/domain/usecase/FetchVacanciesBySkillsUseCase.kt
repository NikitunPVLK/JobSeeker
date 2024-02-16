package com.example.jobseeker.domain.usecase

import com.example.jobseeker.data.network.Result
import com.example.jobseeker.data.network.VacanciesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchVacanciesBySkillsUseCase {
    suspend fun fetchVacanciesBySkills(skills: List<String>): Result {
        return withContext(Dispatchers.IO) {
            try {
                Result.Success(VacanciesApi.retrofitService.getVacanciesBySkills(skills))
            } catch (e: Exception) {
                Result.Failure
            }
        }
    }
}