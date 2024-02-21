package com.example.jobseeker.data.network.repositories.mock

import com.example.jobseeker.data.network.repositories.common.INetworkVacanciesRepository
import com.example.jobseeker.domain.Vacancy

class MockNetworkVacanciesRepository: INetworkVacanciesRepository {
    override suspend fun getVacanciesByParameters(
        search: String,
        category: String,
        experience: String,
        location: String
    ): List<Vacancy> {
        return listOf()
    }

    override suspend fun getVacanciesBySkills(skills: List<String>): List<Vacancy> {
        return listOf()
    }
}