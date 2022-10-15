package com.example.mymovieapp.domain.models.movie

import com.example.mymovieapp.data.network.model.response.movie.MovieGenres

data class MovieDetailsModel(
    val adult: Boolean,
    val backdrop_path: String?,
    val budget: Int,
    val genres: List<MovieGenres>,
    val homepage: String?,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String?,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int?,
    val status: String,
    val tagline: String?,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
)
