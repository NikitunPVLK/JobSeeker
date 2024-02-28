package com.example.jobseeker.ui.viewmodels.search_by_parameters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobseeker.data.network.common.Result
import com.example.jobseeker.domain.models.SearchParameters
import com.example.jobseeker.domain.models.Vacancy
import com.example.jobseeker.domain.usecase.network.FetchVacanciesByParametersUseCase
import com.example.jobseeker.ui.viewmodels.common.ISearchViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchByParametersViewModel @Inject constructor(
    private val useCase: FetchVacanciesByParametersUseCase
) : ViewModel(), ISearchViewModel {

    private val tag = "SearchByParametersVM"

    private var _vacancies = MutableLiveData<List<Vacancy>>()
    override val vacancies: LiveData<List<Vacancy>>
        get() = _vacancies

    lateinit var searchParameters: SearchParameters

    fun searchVacanciesByParameters(searchParameters: SearchParameters) {
        this.searchParameters = searchParameters
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