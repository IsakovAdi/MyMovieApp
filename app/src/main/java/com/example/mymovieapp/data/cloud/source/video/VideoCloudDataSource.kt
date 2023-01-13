package com.example.mymovieapp.data.cloud.source.video

import com.example.mymovieapp.data.models.video.VideosResponseData
import kotlinx.coroutines.flow.Flow

interface VideoCloudDataSource {
    fun getVideos(movieId: Int): Flow<VideosResponseData>
}