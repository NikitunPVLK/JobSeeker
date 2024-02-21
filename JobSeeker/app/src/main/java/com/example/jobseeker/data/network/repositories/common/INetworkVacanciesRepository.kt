package com.example.jobseeker.data.network.repositories.common

import com.example.jobseeker.domain.Vacancy

interface INetworkVacanciesRepository {

    suspend fun getVacanciesByParameters(
        search: String,
        category: String,
        experience: String,
        location: String
    ): List<Vacancy>

    suspend fun getVacanciesBySkills(skills: List<String>): List<Vacancy>
}