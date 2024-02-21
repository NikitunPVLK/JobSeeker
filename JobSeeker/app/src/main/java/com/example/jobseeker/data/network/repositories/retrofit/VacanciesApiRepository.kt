package com.example.jobseeker.data.network.repositories.retrofit

import com.example.jobseeker.data.network.repositories.common.INetworkVacanciesRepository
import com.example.jobseeker.domain.Vacancy
import retrofit2.http.GET
import retrofit2.http.Query

interface VacanciesApiRepository: INetworkVacanciesRepository {
    @GET("parameters")
    override suspend fun getVacanciesByParameters(
        @Query("search") search: String,
        @Query("category") category: String,
        @Query("experience") experience: String,
        @Query("location") location: String
    ): List<Vacancy>

    @GET("skills")
    override suspend fun getVacanciesBySkills(
        @Query("skill") skills: List<String>
    ): List<Vacancy>
}