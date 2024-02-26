package com.example.jobseeker.ui.viewmodels.common

import com.example.jobseeker.domain.usecase.network.IVacanciesNetworkRepository

interface IVacanciesNetworkService {
    fun getVacancyNetworkRepository(): IVacanciesNetworkRepository
}