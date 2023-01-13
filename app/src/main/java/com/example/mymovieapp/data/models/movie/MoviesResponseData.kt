package com.example.mymovieapp.data.models.movie

data class MoviesResponseData(
    val page: Int,
    var movies: List <MovieData>,
    val totalPage: Int,
)
