package com.example.jobseeker.domain.usecase.network

import com.example.jobseeker.data.network.common.Result
import com.example.jobseeker.domain.models.SearchParameters
import com.example.jobseeker.domain.common.ParametersHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchVacanciesByParametersUseCase @Inject constructor(
    private val vacanciesRepository: IVacanciesNetworkRepository,
    private val parametersHandler: ParametersHandler
) {

    suspend fun fetchVacanciesByParameters(searchParameters: SearchParameters): Result {
        return withContext(Dispatchers.IO) {
            try {
                Result.Success(
                    vacanciesRepository.getVacanciesByParameters(
                        searchParameters.keyWords,
                        searchParameters.category,
                        parametersHandler.getFormattedExperience(searchParameters.experience),
                        parametersHandler.getFormattedLocation(searchParameters.location)
                    )
                )
            } catch (e: Exception) {
                Result.Failure(e)
            }
        }
    }
}