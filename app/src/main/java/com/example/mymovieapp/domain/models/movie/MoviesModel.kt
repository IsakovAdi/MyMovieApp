package com.example.mymovieapp.domain.models.movie

data class MoviesModel(
    val page:Int,
    var movies:List<MovieModel>,
    val totalPage:Int
)
