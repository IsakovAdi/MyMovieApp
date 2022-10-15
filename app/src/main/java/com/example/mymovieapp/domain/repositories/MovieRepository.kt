package com.example.mymovieapp.domain.repositories

import com.example.mymovieapp.domain.models.movie.MovieDetailsModel
import com.example.mymovieapp.domain.models.movie.MoviesModel

interface MovieRepository {
    suspend fun getPopularMovie(language: String, page: Int): MoviesModel
    suspend fun getTopRatedMovies(language: String, page: Int): MoviesModel
    suspend fun getUpcomingMovies(language: String, page: Int): MoviesModel
    suspend fun getNowPlayingMovies(language: String, page: Int): MoviesModel
    suspend fun searchMovie(language: String, query: String): MoviesModel
    suspend fun getSimilarMovies(movieId: Int, language: String): MoviesModel
    suspend fun getRecommendMovies(movieId: Int, language: String): MoviesModel
    suspend fun getMovieDetails(movieId: Int, language: String): MovieDetailsModel
}