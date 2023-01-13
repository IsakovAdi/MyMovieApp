package com.example.mymovieapp.domain.models.person


data class PersonsResponseDomain(
    val page: Int,
    val persons: List<PersonDomain>,
    val total_results: Int,
    val total_pages: Int,
)
