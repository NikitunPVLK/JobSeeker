package com.example.jobseeker.data.network.common

import com.example.jobseeker.domain.Vacancy
import java.lang.Exception

sealed class Result {
    data class Success(val vacancies: List<Vacancy>): Result()
    data class Failure(val e: Exception): Result()
}