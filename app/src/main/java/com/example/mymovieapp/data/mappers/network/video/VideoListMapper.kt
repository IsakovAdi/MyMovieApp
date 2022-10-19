package com.example.mymovieapp.data.mappers.network.video

import com.example.mymovieapp.data.mappers.Mapper
import com.example.mymovieapp.data.network.model.response.video.Video
import com.example.mymovieapp.domain.models.video.VideoModel

class VideoListMapper(private val videoMapper: VideoMapper = VideoMapper()) :
    Mapper<List<Video>, List<VideoModel>>(
        fromData = {
            it.map { video ->
                videoMapper.mapData(video)
            }
        }
    )