package com.example.jobseeker.domain.usecase

import com.example.jobseeker.data.network.Result
import com.example.jobseeker.data.network.VacanciesApi
import com.example.jobseeker.domain.SearchParameters
import com.example.jobseeker.ui.viewmodels.ParametersHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchVacanciesByParametersUseCase {

    suspend fun fetchVacanciesByParameters(searchParameters: SearchParameters): Result {
        return withContext(Dispatchers.IO) {
            try {
                val parametersHandler = ParametersHandler()
                Result.Success(
                    VacanciesApi.retrofitService.getVacanciesByParameters(
                        searchParameters.keyWords,
                        searchParameters.category,
                        parametersHandler.getFormattedExperience(searchParameters.experience),
                        parametersHandler.getFormattedLocation(searchParameters.location)
                    )
                )
            } catch (e: Exception) {
                Result.Failure
            }
        }
    }
}