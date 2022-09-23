package com.example.mymovieapp.data.repository

import com.example.mymovieapp.data.network.RetrofitInstance
import com.example.mymovieapp.data.Utils
import com.example.mymovieapp.data.VideoMapper
import com.example.mymovieapp.data.network.VideoRepositoryData
import com.example.mymovieapp.domain.models.VideosModel
import com.example.mymovieapp.domain.repository.VideoRepository
import retrofit2.Response

class VideoRepositoryImpl(private val repository: VideoRepositoryData) : VideoRepository {
    private val mapper = VideoMapper()
    override suspend fun getVideos(movieId: Int): VideosModel {
        val response = repository.getVideos(movieId)
        return mapper.mapEntityVideosToModel(response.body()!!)
    }
}