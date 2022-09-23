package com.example.mymovieapp.data

import com.example.mymovieapp.data.entities.Video
import com.example.mymovieapp.data.entities.Videos
import com.example.mymovieapp.domain.models.VideoModel
import com.example.mymovieapp.domain.models.VideosModel

class VideoMapper {
    fun mapEntityVideoToModel(video: Video): VideoModel = VideoModel(
        id = video.id,
        iso31661 = video.iso31661,
        iso6391 = video.iso6391,
        key = video.key,
        name = video.name,
        official = video.official,
        publishedAt = video.publishedAt,
        site = video.site,
        size = video.size,
        type = video.type
    )

    fun mapListEntityToModel(videos: List<Video>) = videos.map {
        mapEntityVideoToModel(it)
    }

    fun mapEntityVideosToModel(videos:Videos):VideosModel = VideosModel(
        id = videos.id,
        trailerList = mapListEntityToModel(videos.trailerList)
    )
}