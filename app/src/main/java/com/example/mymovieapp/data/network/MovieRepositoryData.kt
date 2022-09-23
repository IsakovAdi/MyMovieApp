package com.example.mymovieapp.data.network

import com.example.mymovieapp.data.entities.Movie
import com.example.mymovieapp.data.entities.Movies
import retrofit2.Response

interface MovieRepositoryData {
    suspend fun getPopularMovie(language: String, page: Int): Response<Movies>
    suspend fun getTopRatedMovies(language: String, page: Int): Response<Movies>
    suspend fun getUpcomingMovies(language: String, page: Int): Response<Movies>
    suspend fun getNowPlayingMovies(language: String, page: Int): Response<Movies>
    suspend fun searchMovie(language: String, query: String): Response<Movies>
    suspend fun getMovieDetails(movieId: Int, language: String): Response<Movie>
}