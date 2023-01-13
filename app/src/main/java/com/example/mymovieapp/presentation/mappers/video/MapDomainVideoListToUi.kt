package com.example.mymovieapp.presentation.mappers.video

import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.models.video.VideoDomain
import com.example.mymovieapp.presentation.models.video.VideoUi

class MapDomainVideoListToUi(
    private val mapper: Mapper<VideoDomain, VideoUi>,
) : Mapper<List<VideoDomain>, List<VideoUi>> {
    override fun map(from: List<VideoDomain>) = from.run {
        map(mapper::map)
    }
}