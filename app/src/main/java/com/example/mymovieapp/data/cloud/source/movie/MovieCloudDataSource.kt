package com.example.mymovieapp.data.cloud.source.movie

import com.example.mymovieapp.data.models.movie.MovieDetailsData
import com.example.mymovieapp.data.models.movie.MoviesResponseData
import com.example.mymovieapp.domain.DataRequestState
import kotlinx.coroutines.flow.Flow

interface MovieCloudDataSource {
    fun getPopularMovie(page: Int): Flow<MoviesResponseData>
    fun getTopRatedMovies(page: Int): Flow<MoviesResponseData>
    fun getUpcomingMovies(page: Int): Flow<MoviesResponseData>
    fun getNowPlayingMovies(page: Int): Flow<MoviesResponseData>
//    fun searchMovie(query: String?): Flow<MoviesResponseData>
    suspend fun searchMovie(query: String?): DataRequestState<MoviesResponseData>
    fun getSimilarMovies(movieId: Int): Flow<MoviesResponseData>
    fun getRecommendMovies(movieId: Int): Flow<MoviesResponseData>
    suspend fun getMovieDetails(movieId: Int): DataRequestState<MovieDetailsData>
}