package com.example.jobseeker.domain.usecase.network

import com.example.jobseeker.domain.models.Vacancy

interface IVacanciesNetworkRepository {

    suspend fun getVacanciesByParameters(
        search: String,
        category: String,
        experience: String,
        location: String
    ): List<Vacancy>

    suspend fun getVacanciesBySkills(skills: List<String>): List<Vacancy>
}