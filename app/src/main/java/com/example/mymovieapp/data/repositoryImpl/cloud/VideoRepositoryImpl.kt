package com.example.mymovieapp.data.repositoryImpl.cloud

import com.example.mymovieapp.data.cloud.source.video.VideoCloudDataSource
import com.example.mymovieapp.data.models.video.VideosResponseData
import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.models.video.VideosResponseDomain
import com.example.mymovieapp.domain.repositories.network.VideoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class VideoRepositoryImpl(
    private val videoCloudDataSource: VideoCloudDataSource,
    private val mapper: Mapper<VideosResponseData, VideosResponseDomain>,
) : VideoRepository {
    override fun getVideos(movieId: Int): Flow<VideosResponseDomain> =
        videoCloudDataSource.getVideos(movieId = movieId)
            .map(mapper::map)
            .flowOn(Dispatchers.Default)
}