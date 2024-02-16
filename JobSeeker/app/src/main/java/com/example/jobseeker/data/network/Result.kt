package com.example.jobseeker.data.network

import com.example.jobseeker.domain.Vacancy

sealed class Result {
    data class Success(val vacancies: List<Vacancy>): Result()
    object Failure: Result()
}