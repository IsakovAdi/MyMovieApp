package com.example.mymovieapp.data.cloud.api

import com.example.mymovieapp.data.cloud.Endpoints
import com.example.mymovieapp.data.cloud.Utils
import com.example.mymovieapp.data.cloud.model.video.VideosResponseCloud
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VideoApi {
    @GET(Endpoints.TRAILER.MOVIE_TRAILERS)
    suspend fun getTrailers(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String = "ru",
    ): Response<VideosResponseCloud>
}