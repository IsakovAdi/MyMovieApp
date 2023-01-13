package com.example.mymovieapp.data.cloud.source.video

import com.example.mymovieapp.data.cloud.api.VideoApi
import com.example.mymovieapp.data.cloud.model.video.VideosResponseCloud
import com.example.mymovieapp.data.models.video.VideosResponseData
import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.helper.DispatchersProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class VideoCloudDataSourceImpl(
    private val api: VideoApi,
    private val mapper: Mapper<VideosResponseCloud, VideosResponseData>,
    private val dispatchersProvider: DispatchersProvider,
) : VideoCloudDataSource {
    override fun getVideos(
        movieId: Int,
    ): Flow<VideosResponseData> = flow {
        emit(api.getTrailers(id = movieId))
    }.flowOn(dispatchersProvider.io())
        .map { it.body()!! }
        .map(mapper::map)
        .flowOn(dispatchersProvider.default())
}