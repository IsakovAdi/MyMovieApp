package com.example.mymovieapp.data.api

import com.example.mymovieapp.data.network.Endpoints.Movie.MOVIE_DETAILS
import com.example.mymovieapp.data.network.Endpoints.Movie.NOW_PLAYING
import com.example.mymovieapp.data.network.model.response.movie.Movie
import com.example.mymovieapp.data.network.model.response.movie.Movies
import com.example.mymovieapp.data.network.Endpoints.Movie.POPULAR
import com.example.mymovieapp.data.network.Endpoints.Movie.RECOMMENDATIONS
import com.example.mymovieapp.data.network.Endpoints.Movie.SEARCH_MOVIE
import com.example.mymovieapp.data.network.Endpoints.Movie.SIMILAR
import com.example.mymovieapp.data.network.Endpoints.Movie.TOP_RATED
import com.example.mymovieapp.data.network.Endpoints.Movie.UPCOMING
import com.example.mymovieapp.data.network.model.response.movie.MovieDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET(POPULAR)
    suspend fun getPopular(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("page") page: Int?,
    ): Movies

    @GET(TOP_RATED)
    suspend fun getTops(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("page") page: Int?,
    ): Movies

    @GET(UPCOMING)
    suspend fun getUpcoming(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("page") page: Int?,
    ): Movies

    @GET(NOW_PLAYING)
    suspend fun getNowPlaying(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("page") page: Int?,
    ): Movies

    @GET(SEARCH_MOVIE)
    suspend fun search(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("query") query: String?,
    ): Movies

    @GET(MOVIE_DETAILS)
    suspend fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String?,
    ): MovieDetails

    @GET(SIMILAR)
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int?,
    ): Movies

    @GET(RECOMMENDATIONS)
    suspend fun getRecommendMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int?,
    ): Movies

}