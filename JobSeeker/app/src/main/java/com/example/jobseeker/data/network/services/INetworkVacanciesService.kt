package com.example.jobseeker.data.network.services

import com.example.jobseeker.data.network.repositories.common.INetworkVacanciesRepository
import com.example.jobseeker.domain.Vacancy

interface INetworkVacanciesService {
    fun getNetworkVacanciesRepository(): INetworkVacanciesRepository
}