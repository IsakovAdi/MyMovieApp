package com.example.mymovieapp.data.api

import com.example.mymovieapp.data.network.Endpoints
import com.example.mymovieapp.data.network.model.response.video.Videos
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VideoApi {
    @GET(Endpoints.TRAILER.MOVIE_TRAILERS)
    suspend fun getTrailers(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language:String
    ): Videos
}