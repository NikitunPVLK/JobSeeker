package com.example.jobseeker.ui.viewmodels.common

import androidx.lifecycle.LiveData
import com.example.jobseeker.domain.Vacancy

interface ISearchViewModel {
    val vacancies: LiveData<List<Vacancy>>
}