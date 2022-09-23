package com.example.mymovieapp.data.network

import com.example.mymovieapp.data.entities.Videos
import retrofit2.Response

interface VideoRepositoryData {
    suspend fun getVideos(movieId: Int): Response<Videos>
}