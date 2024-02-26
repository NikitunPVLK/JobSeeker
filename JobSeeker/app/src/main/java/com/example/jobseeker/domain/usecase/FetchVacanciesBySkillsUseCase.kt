package com.example.jobseeker.domain.usecase

import com.example.jobseeker.data.network.common.Result
import com.example.jobseeker.data.network.repositories.common.IVacanciesNetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchVacanciesBySkillsUseCase(private val vacanciesApiRepository: IVacanciesNetworkRepository) {
    suspend fun fetchVacanciesBySkills(skills: List<String>): Result {
        return withContext(Dispatchers.IO) {
            try {
                Result.Success(vacanciesApiRepository.getVacanciesBySkills(skills))
            } catch (e: Exception) {
                Result.Failure(e)
            }
        }
    }
}