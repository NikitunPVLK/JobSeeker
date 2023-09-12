package com.example.jobseeker.network

import com.example.jobseeker.model.Vacancy
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "http://192.168.0.103:8080/vacancies/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

interface VacanciesApiService {
    @GET("parameters")
    suspend fun getVacanciesByParameters(
        @Query("search") search: String,
        @Query("category") category: String,
        @Query("experience") experience: String,
        @Query("location") location: String
    ): List<Vacancy>

    @GET("skills")
    suspend fun getVacanciesBySkills(
        @Query("skill") skills: List<String>
    ): List<Vacancy>
}

object VacanciesApi {
    val retrofitService: VacanciesApiService by lazy {
        retrofit.create(VacanciesApiService::class.java)
    }
}