package com.example.mymovieapp.domain.repositories.network

import com.example.mymovieapp.domain.DataRequestState
import com.example.mymovieapp.domain.models.movie.MovieDetailsDomain
import com.example.mymovieapp.domain.models.movie.MoviesResponseDomain
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovie(page: Int): Flow<MoviesResponseDomain>
    fun getTopRatedMovies(page: Int): Flow<MoviesResponseDomain>
    fun getUpcomingMovies(page: Int): Flow<MoviesResponseDomain>
    fun getNowPlayingMovies(page: Int): Flow<MoviesResponseDomain>
    fun searchMovie(query: String?): Flow<MoviesResponseDomain>
    fun getSimilarMovies(movieId: Int): Flow<MoviesResponseDomain>
    fun getRecommendMovies(movieId: Int): Flow<MoviesResponseDomain>
    suspend fun getMovieDetails(movieId: Int): DataRequestState<MovieDetailsDomain>
}