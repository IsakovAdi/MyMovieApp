package com.example.mymovieapp.domain.models.movie

data class MoviesResponseDomain(
    val page: Int,
    var movies: List<MovieDomain>,
    val totalPage: Int,
)
