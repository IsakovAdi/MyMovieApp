package com.example.mymovieapp.presentation.models.movie

data class MoviesResponseUi(
    val page: Int,
    var movies: List<MovieUi>,
    val totalPage: Int
)
