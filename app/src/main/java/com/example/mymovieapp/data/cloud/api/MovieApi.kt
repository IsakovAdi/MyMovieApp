package com.example.mymovieapp.data.cloud.api

import com.example.mymovieapp.data.cloud.Endpoints.Movie.MOVIE_DETAILS
import com.example.mymovieapp.data.cloud.Endpoints.Movie.NOW_PLAYING
import com.example.mymovieapp.data.cloud.Endpoints.Movie.POPULAR
import com.example.mymovieapp.data.cloud.Endpoints.Movie.RECOMMENDATIONS
import com.example.mymovieapp.data.cloud.Endpoints.Movie.SEARCH_MOVIE
import com.example.mymovieapp.data.cloud.Endpoints.Movie.SIMILAR
import com.example.mymovieapp.data.cloud.Endpoints.Movie.TOP_RATED
import com.example.mymovieapp.data.cloud.Endpoints.Movie.UPCOMING
import com.example.mymovieapp.data.cloud.Utils
import com.example.mymovieapp.data.cloud.model.movie.MovieDetailsCloud
import com.example.mymovieapp.data.cloud.model.movie.MoviesResponseCloud
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET(POPULAR)
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String? = "ru",
        @Query("page") page: Int?,
    ): Response<MoviesResponseCloud>

    @GET(TOP_RATED)
    suspend fun getTopRatingMovies(
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String? = "ru",
        @Query("page") page: Int?,
    ): Response<MoviesResponseCloud>

    @GET(UPCOMING)
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String? = "ru",
        @Query("page") page: Int?,
    ): Response<MoviesResponseCloud>

    @GET(NOW_PLAYING)
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String? = "ru",
        @Query("page") page: Int?,
    ): Response<MoviesResponseCloud>

    @GET(SEARCH_MOVIE)
    suspend fun searchMovie(
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String? = "ru",
        @Query("query") query: String?,
    ): Response<MoviesResponseCloud>

    @GET(SIMILAR)
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String? = "ru",
        @Query("page") page: Int? = 1,
    ): Response<MoviesResponseCloud>

    @GET(RECOMMENDATIONS)
    suspend fun getRecommendMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String? = "ru",
        @Query("page") page: Int? = 1,
    ): Response<MoviesResponseCloud>

    @GET(MOVIE_DETAILS)
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String? = "ru",
    ): Response<MovieDetailsCloud>

}