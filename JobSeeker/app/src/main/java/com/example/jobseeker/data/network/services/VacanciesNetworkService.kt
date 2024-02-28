package com.example.jobseeker.data.network.services

import com.example.jobseeker.domain.usecase.network.IVacanciesNetworkRepository
import com.example.jobseeker.data.network.repositories.mock.MockVacanciesNetworkRepository
import com.example.jobseeker.data.network.repositories.retrofit.VacanciesNetworkRepository
import com.example.jobseeker.domain.di.ApplicationScope
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@ApplicationScope
object VacanciesNetworkService{
    private const val BASE_URL = "http://192.168.0.103:8080/vacancies/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()

    private val retrofitService: IVacanciesNetworkRepository by lazy {
        retrofit.create(VacanciesNetworkRepository::class.java)
    }

    private val mockService: IVacanciesNetworkRepository by lazy {
        MockVacanciesNetworkRepository()
    }

    fun getVacancyNetworkRepository(): IVacanciesNetworkRepository {
//        return retrofitService
        return mockService
    }
}