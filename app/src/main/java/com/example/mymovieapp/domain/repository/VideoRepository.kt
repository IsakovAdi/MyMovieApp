package com.example.mymovieapp.domain.repository

import com.example.mymovieapp.domain.models.VideosModel
import retrofit2.Response

interface VideoRepository {
    suspend fun getVideos(movieId: Int): VideosModel
}