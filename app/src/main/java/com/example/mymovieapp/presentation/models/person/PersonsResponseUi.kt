package com.example.mymovieapp.presentation.models.person


data class PersonsResponseUi(
    val page: Int,
    val persons: List<PersonUi>,
    val total_results: Int,
    val total_pages: Int,
)
