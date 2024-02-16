package com.example.jobseeker.domain

data class SearchParameters(
    val keyWords: String,
    val category: String,
    val experience: String,
    val location: String
)
