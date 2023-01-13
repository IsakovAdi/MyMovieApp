package com.example.mymovieapp.data.mappers.video

import com.example.mymovieapp.data.models.video.VideoData
import com.example.mymovieapp.data.models.video.VideosResponseData
import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.models.video.VideoDomain
import com.example.mymovieapp.domain.models.video.VideosResponseDomain

class MapDataVideoResponseToDomain(
    private val mapper: Mapper<List<VideoData>, List<VideoDomain>>,
) : Mapper<VideosResponseData, VideosResponseDomain> {
    override fun map(from: VideosResponseData) = from.run {
        VideosResponseDomain(
            id = id,
            trailerList = mapper.map(trailerList)
        )
    }
}