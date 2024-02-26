package com.example.jobseeker.ui.viewmodels.common

import com.example.jobseeker.data.network.repositories.common.IVacanciesNetworkRepository

interface IVacanciesNetworkService {
    fun getVacancyNetworkRepository(): IVacanciesNetworkRepository
}