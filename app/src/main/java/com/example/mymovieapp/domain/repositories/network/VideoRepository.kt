package com.example.mymovieapp.domain.repositories.network

import com.example.mymovieapp.domain.models.video.VideosModel

interface VideoRepository {
    suspend fun getVideos(movieId: Int,language:String): VideosModel
}