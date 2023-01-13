package com.example.mymovieapp.presentation.mappers.video

import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.models.video.VideoDomain
import com.example.mymovieapp.presentation.models.video.VideoUi

class MapDomainVideoToUi : Mapper<VideoDomain, VideoUi> {
    override fun map(from: VideoDomain) = from.run {
        VideoUi(
            id = id,
            iso31661 = iso31661,
            iso6391 = iso6391,
            key = key,
            name = name,
            official = official,
            publishedAt = publishedAt,
            site = site,
            size = size,
            type = type
        )
    }
}