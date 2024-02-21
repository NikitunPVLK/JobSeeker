package com.example.jobseeker.data.network.repositories.retrofit

import com.example.jobseeker.data.network.repositories.common.INetworkVacanciesRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object VacanciesApi {
    private const val BASE_URL = "http://192.168.0.103:8080/vacancies/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()

    val retrofitService: INetworkVacanciesRepository by lazy {
        retrofit.create(VacanciesApiRepository::class.java)
    }
}