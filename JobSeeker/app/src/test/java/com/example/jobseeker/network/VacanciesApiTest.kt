package com.example.jobseeker.network

import com.example.jobseeker.data.network.repositories.retrofit.VacanciesApi

import org.junit.Test

class VacanciesApiTest {

    @Test
    fun getRetrofitService() {
        VacanciesApi.retrofitService
    }
}