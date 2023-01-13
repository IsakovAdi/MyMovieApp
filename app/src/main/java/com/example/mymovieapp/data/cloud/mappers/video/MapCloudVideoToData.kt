package com.example.mymovieapp.data.cloud.mappers.video

import com.example.mymovieapp.data.cloud.model.video.VideoCloud
import com.example.mymovieapp.data.models.video.VideoData
import com.example.mymovieapp.domain.Mapper

class MapCloudVideoToData : Mapper<VideoCloud, VideoData> {
    override fun map(from: VideoCloud) = from.run {
        VideoData(
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