package com.example.jobseeker.domain.usecase.network

import com.example.jobseeker.data.network.common.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchVacanciesBySkillsUseCase @Inject constructor(
    private val vacanciesRepository: IVacanciesNetworkRepository
) {
    suspend fun fetchVacanciesBySkills(skills: List<String>): Result {
        return withContext(Dispatchers.IO) {
            try {
                Result.Success(vacanciesRepository.getVacanciesBySkills(skills))
            } catch (e: Exception) {
                Result.Failure(e)
            }
        }
    }
}