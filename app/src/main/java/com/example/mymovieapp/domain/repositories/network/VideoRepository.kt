package com.example.mymovieapp.domain.repositories.network

import com.example.mymovieapp.domain.models.video.VideosResponseDomain
import kotlinx.coroutines.flow.Flow

interface VideoRepository {
    fun getVideos(movieId: Int): Flow<VideosResponseDomain>
}