package com.example.mymovieapp.data.mappers.video

import com.example.mymovieapp.data.mappers.Mapper
import com.example.mymovieapp.data.network.model.response.video.Videos
import com.example.mymovieapp.domain.models.video.VideosModel

class VideosMapper(private val videoMapper: VideoListMapper = VideoListMapper()) :
    Mapper<Videos, VideosModel>(
        fromData = {
            VideosModel(
                id = it.id,
                trailerList = videoMapper.mapData(it.trailerList)
            )
        }
    )