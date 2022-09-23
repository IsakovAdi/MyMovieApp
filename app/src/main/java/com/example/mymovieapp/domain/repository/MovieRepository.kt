package com.example.mymovieapp.domain.repository

import com.example.mymovieapp.domain.models.MovieModel
import com.example.mymovieapp.domain.models.MoviesModel
import retrofit2.Response

interface MovieRepository {
    suspend fun getPopularMovie(language: String, page: Int): MoviesModel?
    suspend fun getTopRatedMovies(language: String, page: Int): MoviesModel?
    suspend fun getUpcomingMovies(language: String, page: Int): MoviesModel?
    suspend fun getNowPlayingMovies(language: String, page: Int): MoviesModel?
    suspend fun searchMovie(language: String, query: String): MoviesModel?
    suspend fun getMovieDetails(movieId: Int, language: String): MovieModel?
}