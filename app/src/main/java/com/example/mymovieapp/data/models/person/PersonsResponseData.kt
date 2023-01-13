package com.example.mymovieapp.data.models.person


class PersonsResponseData(
    val page: Int,
    val persons: List<PersonData>,
    val total_results: Int,
    val total_pages: Int,
)
