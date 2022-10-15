package com.example.mymovieapp.data.mappers.video

import com.example.mymovieapp.data.mappers.Mapper
import com.example.mymovieapp.data.network.model.response.video.Video
import com.example.mymovieapp.domain.models.video.VideoModel

class VideoMapper : Mapper<Video, VideoModel>(
    fromData = {
        VideoModel(
            id = it.id,
            iso31661 = it.iso31661,
            iso6391 = it.iso6391,
            key = it.key,
            name = it.name,
            official = it.official,
            publishedAt = it.publishedAt,
            site = it.site,
            size = it.size,
            type = it.type
        )
    }
)