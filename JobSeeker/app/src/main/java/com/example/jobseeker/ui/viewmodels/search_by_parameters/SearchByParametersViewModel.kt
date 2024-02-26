package com.example.jobseeker.ui.viewmodels.search_by_parameters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobseeker.data.network.common.Result
import com.example.jobseeker.data.network.services.VacanciesNetworkService
import com.example.jobseeker.domain.SearchParameters
import com.example.jobseeker.domain.Vacancy
import com.example.jobseeker.domain.usecase.FetchVacanciesByParametersUseCase
import com.example.jobseeker.ui.viewmodels.common.ISearchViewModel
import kotlinx.coroutines.launch

class SearchByParametersViewModel(
    private val useCase: FetchVacanciesByParametersUseCase
) : ViewModel(), ISearchViewModel {

    private val tag = "SearchByParametersVM"

    private var _vacancies = MutableLiveData<List<Vacancy>>()
    override val vacancies: LiveData<List<Vacancy>>
        get() = _vacancies

    private lateinit var _searchParameters: SearchParameters
    val searchParameters: SearchParameters
        get() = _searchParameters

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