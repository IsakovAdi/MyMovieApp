package com.example.mymovieapp.data.api

import com.example.mymovieapp.data.entities.Movie
import com.example.mymovieapp.data.entities.Movies
import com.example.mymovieapp.data.entities.Videos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    suspend fun getPopular(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("page") page: Int?
    ): Response<Movies>

    @GET("movie/top_rated")
    suspend fun getTops(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("page") page: Int?
    ): Response<Movies>

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("page") page: Int?
    ): Response<Movies>

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("page") page: Int?
    ): Response<Movies>

    @GET("search/movie")
    suspend fun search(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("query") query: String?
    ): Response<Movies>

    @GET("movie/{id}/videos")
    suspend fun getTrailers(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Response<Videos>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String?
    ): Response<Movie>
}