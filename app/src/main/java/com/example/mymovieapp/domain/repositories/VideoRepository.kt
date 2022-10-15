package com.example.mymovieapp.domain.repositories

import com.example.mymovieapp.domain.models.video.VideosModel

interface VideoRepository {
    suspend fun getVideos(movieId: Int): VideosModel
}