package com.example.mymovieapp.data.network

import com.example.mymovieapp.data.Utils
import com.example.mymovieapp.data.entities.Videos
import retrofit2.Response

class VideoRepositoryDataImpl : VideoRepositoryData {
    override suspend fun getVideos(movieId: Int): Response<Videos> =
        RetrofitInstance.movieApi.getTrailers(movieId, Utils.API_KEY)
}