package com.example.jobseeker.data.network.services

import com.example.jobseeker.data.network.repositories.common.INetworkVacanciesRepository
import com.example.jobseeker.data.network.repositories.mock.MockNetworkVacanciesRepository
import com.example.jobseeker.data.network.repositories.retrofit.VacanciesApi

class NetworkVacanciesService: INetworkVacanciesService {
    override fun getNetworkVacanciesRepository(): INetworkVacanciesRepository {
//        return VacanciesApi.retrofitService
        return MockNetworkVacanciesRepository()
    }
}