package com.example.mymovieapp.presentation.mappers.video

import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.models.video.VideoDomain
import com.example.mymovieapp.domain.models.video.VideosResponseDomain
import com.example.mymovieapp.presentation.models.video.VideoUi
import com.example.mymovieapp.presentation.models.video.VideosResponseUi

class MapDomainVideoResponseToUi(
    private val mapper: Mapper<List<VideoDomain>, List<VideoUi>>,
) : Mapper<VideosResponseDomain, VideosResponseUi> {
    override fun map(from: VideosResponseDomain) = from.run {
        VideosResponseUi(
            id = id,
            trailerList = mapper.map(trailerList))
    }
}