package com.example.mymovieapp.data.mappers.video

import com.example.mymovieapp.data.models.video.VideoData
import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.models.video.VideoDomain

class MapDataVideoToDomain : Mapper<VideoData, VideoDomain> {
    override fun map(from: VideoData) = from.run {
        VideoDomain(
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