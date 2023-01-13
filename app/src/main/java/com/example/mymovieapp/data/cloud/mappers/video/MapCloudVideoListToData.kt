package com.example.mymovieapp.data.cloud.mappers.video

import com.example.mymovieapp.data.cloud.model.video.VideoCloud
import com.example.mymovieapp.data.models.video.VideoData
import com.example.mymovieapp.domain.Mapper

class MapCloudVideoListToData(private val mapper: Mapper<VideoCloud, VideoData>) :
    Mapper<List<VideoCloud>, List<VideoData>> {
    override fun map(from: List<VideoCloud>) = from.run {
        map(mapper::map)
    }
}