package com.example.mymovieapp.data.network.service

import com.example.mymovieapp.data.network.Resource
import com.example.mymovieapp.data.api.VideoApi
import com.example.mymovieapp.data.mappers.network.video.VideosMapper
import com.example.mymovieapp.data.network.Utils
import com.example.mymovieapp.domain.models.video.VideosModel
import com.example.mymovieapp.domain.repositories.network.VideoRepository

class VideoRepositoryImpl(private val api: VideoApi) : VideoRepository, BaseRepository() {
    private val videosMapper = VideosMapper()
    override suspend fun getVideos(movieId: Int, language:String): VideosModel =
        safeApiCall {
            api.getTrailers(movieId, Utils.API_KEY, language)
        }.let {response->
            when(response){
                is Resource.Success -> videosMapper.mapData(response.value)
                is Resource.Failure ->{
                    throw response.serviceException
                }
            }
        }
}