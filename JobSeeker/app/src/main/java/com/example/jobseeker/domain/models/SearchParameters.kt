package com.example.jobseeker.domain.models

data class SearchParameters(
    val keyWords: String,
    val category: String,
    val experience: String,
    val location: String
)
