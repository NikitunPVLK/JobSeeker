package com.example.jobseeker.ui.viewmodels.search_by_parameters

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.jobseeker.data.network.common.Result
import com.example.jobseeker.data.network.repositories.retrofit.VacanciesApi
import com.example.jobseeker.data.network.services.NetworkVacanciesService
import com.example.jobseeker.domain.SearchParameters
import com.example.jobseeker.domain.usecase.FetchVacanciesByParametersUseCase
import com.example.jobseeker.ui.viewmodels.common.ISearchViewModel
import com.example.jobseeker.ui.viewmodels.common.TempSearchViewModel
import kotlinx.coroutines.launch

class SearchByParametersViewModel() : TempSearchViewModel(), ISearchViewModel {

    private val tag = "SearchByParametersVM"

    private lateinit var _searchParameters: SearchParameters
    val searchParameters: SearchParameters
        get() = _searchParameters

    private val vacanciesService = NetworkVacanciesService()

    private val useCase = FetchVacanciesByParametersUseCase(vacanciesService.getNetworkVacanciesRepository())

    fun searchVacanciesByParameters(searchParameters: SearchParameters) {
        _searchParameters = searchParameters
        viewModelScope.launch {
            when (val result = useCase.fetchVacanciesByParameters(searchParameters)) {
                is Result.Success -> _vacancies.value = result.vacancies
                is Result.Failure -> {
                    Log.e(tag, "FetchVacanciesByParametersUseCase failure: ${result.e.message}")
                    _vacancies.value = listOf()
                }
            }
        }
    }
}