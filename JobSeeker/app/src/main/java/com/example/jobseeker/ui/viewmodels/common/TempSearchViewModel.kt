package com.example.jobseeker.ui.viewmodels.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jobseeker.domain.Vacancy

open class TempSearchViewModel() : ViewModel() {
    protected var _vacancies = MutableLiveData<List<Vacancy>>()
    val vacancies: LiveData<List<Vacancy>>
        get() = _vacancies
}