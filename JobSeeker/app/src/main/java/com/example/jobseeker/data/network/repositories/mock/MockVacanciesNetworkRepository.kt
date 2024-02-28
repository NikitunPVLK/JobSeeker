package com.example.jobseeker.data.network.repositories.mock

import com.example.jobseeker.domain.usecase.network.IVacanciesNetworkRepository
import com.example.jobseeker.domain.models.Vacancy

class MockVacanciesNetworkRepository : IVacanciesNetworkRepository {

    private fun vacancyList(): List<Vacancy> {
        return listOf(
            Vacancy(
                0,
                "http::/vacancy_1",
                "Vacancy 1",
                "Company 1",
                "1",
                "Location 1",
                "Description 1"
            ),
            Vacancy(
                2,
                "http::/vacancy_2",
                "Vacancy 2",
                "Company 2",
                "2",
                "Location 2",
                "Description 2"
            ),
            Vacancy(
                3,
                "http::/vacancy_3",
                "Vacancy 3",
                "Company 3",
                "3",
                "Location 3",
                "Description 3"
            ),
        )
    }
    override suspend fun getVacanciesByParameters(
        search: String,
        category: String,
        experience: String,
        location: String
    ): List<Vacancy> {
        return vacancyList()
    }

    override suspend fun getVacanciesBySkills(skills: List<String>): List<Vacancy> {
        return vacancyList()
    }
}