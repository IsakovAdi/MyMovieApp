package com.example.mymovieapp.data.mappers.video

import com.example.mymovieapp.data.models.video.VideoData
import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.models.video.VideoDomain

class MapDataVideoListToDomain(
    private val mapper: Mapper<VideoData, VideoDomain>,
) : Mapper<List<VideoData>, List<VideoDomain>> {
    override fun map(from: List<VideoData>) = from.run {
        map(mapper::map)
    }
}